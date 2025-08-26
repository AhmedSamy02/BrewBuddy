package com.example.brewbuddy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.domain.usecase.GetCoffeesByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoffeesByCategoryUseCase: GetCoffeesByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // TODO: Load initial data
    }

    fun loadBestSeller() {
        // TODO: Implement loading best seller
    }

    fun loadRecommendations() {
        // TODO: Implement loading recommendations
    }

    fun saveUserName(name: String) {
        // TODO: Implement saving user name
    }
}

data class HomeUiState(
    val bestSeller: Any? = null, // TODO: Replace with proper type
    val recommendations: List<Any> = emptyList(), // TODO: Replace with proper type
    val userName: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
