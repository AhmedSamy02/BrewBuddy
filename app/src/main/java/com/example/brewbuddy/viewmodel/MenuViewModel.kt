package com.example.brewbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.data.model.Category
import com.example.brewbuddy.data.model.CoffeeItem
import com.example.brewbuddy.data.repository.CoffeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MenuViewModel(
    private val repo: CoffeeRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<CoffeeItem>>(emptyList())
    val items: StateFlow<List<CoffeeItem>> = _items.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val filteredItems: StateFlow<List<CoffeeItem>> = combine(_items, _query) { items, q ->
        if (q.isBlank()) {
            items
        } else {
            val queryLower = q.lowercase()
            items.filter { coffee ->
                coffee.name.contains(queryLower, ignoreCase = true) ||
                        coffee.ingredients.any { ingredient ->
                            ingredient.contains(queryLower, ignoreCase = true)
                        }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun load(category: Category) {
        viewModelScope.launch {
            val list = when (category) {
                Category.HOT -> repo.refreshAndCacheHot()
                Category.COLD -> repo.refreshAndCacheIced()
            }
            _items.value = list
        }
    }

    fun setQuery(q: String) {
        _query.value = q
    }
}
