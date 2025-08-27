package com.example.brewbuddy.data.repository
import com.example.brewbuddy.data.local.database.AppDatabase
import com.example.brewbuddy.data.local.entity.CoffeeEntity
import com.example.brewbuddy.data.local.entity.OrderEntity
import com.example.brewbuddy.data.model.Category
import com.example.brewbuddy.data.model.CoffeeItem
import com.example.brewbuddy.data.remote.api.CoffeeApiModel
import com.example.brewbuddy.data.remote.api.CoffeeApiService

class CoffeeRepository(
    private val api: CoffeeApiService,
    private val db: AppDatabase
) {
    private fun apiToEntity(api: CoffeeApiModel, category: Category): CoffeeEntity {
        val id = api.id ?: (api.title?.hashCode() ?: 0)
        val price = 2.5 + (id % 6) * 0.5
        return CoffeeEntity(
            id = id,
            title = api.title ?: "Unknown",
            description = api.description,
            ingredients = api.ingredients?.joinToString(",") ?: "",
            imageUrl = api.image,
            price = price,
            category = category.name
        )
    }

    private fun entityToItem(entity: CoffeeEntity): CoffeeItem {
        return CoffeeItem(
            id = entity.id,
            name = entity.title,
            description = entity.description,
            ingredients = entity.ingredients.split(",").map { it.trim() },
            imageUrl = entity.imageUrl,
            price = entity.price,
            category = entity.category
        )
    }

    suspend fun refreshAndCacheHot() : List<CoffeeItem> {
        return try {
            val remote = api.getHot()
            val entities = remote.map { apiToEntity(it, Category.HOT) }
            db.coffeeDao().upsertAll(entities)
            entities.map { entityToItem(it) }   // ✅ no unresolved reference
        } catch (e: Exception) {
            db.coffeeDao().getByCategory("HOT").map { entityToItem(it) }
        }
    }

    suspend fun refreshAndCacheIced() : List<CoffeeItem> {
        return try {
            val remote = api.getIced()
            val entities = remote.map { apiToEntity(it, Category.COLD) }
            db.coffeeDao().upsertAll(entities)
            entities.map { entityToItem(it) }
        } catch (e: Exception) {
            db.coffeeDao().getByCategory("COLD").map { entityToItem(it) }
        }
    }

    suspend fun toggleFavorite(coffeeId: Int) {}
    suspend fun addOrder(order: OrderEntity) = db.orderDao().insert(order)
    suspend fun getOrders() = db.orderDao().getAllOrders()
}
