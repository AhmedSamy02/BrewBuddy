package com.example.brewbuddy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.data.local.database.entities.CoffeeEntity
import com.example.brewbuddy.domain.repository.CoffeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val repo: CoffeeRepository
) : ViewModel() {

    private val _coffeeList = MutableStateFlow<List<CoffeeEntity>>(emptyList())
    val coffeeList: StateFlow<List<CoffeeEntity>> = _coffeeList

    private var allItems: List<CoffeeEntity> = emptyList()

    init { loadCoffees() }

    private fun loadCoffees() {
        viewModelScope.launch {
            try {
                val hot = repo.fetchHotCoffees()
                val cold = repo.fetchColdCoffees()
                allItems = (hot + cold).sortedBy { it.title }
                _coffeeList.value = allItems
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun filter(query: String) {
        _coffeeList.value = if (query.isBlank()) {
            allItems
        } else {
            val q = query.trim().lowercase()
            allItems.filter {
                it.title.lowercase().contains(q) ||
                        it.ingredients.lowercase().contains(q)
            }
        }
    }

    fun filterByCategory(category: String) {
        _coffeeList.value = allItems.filter { it.category == category }
    }
}
