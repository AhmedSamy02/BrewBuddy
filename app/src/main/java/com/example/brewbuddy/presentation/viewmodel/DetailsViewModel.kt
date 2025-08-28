package com.example.brewbuddy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.brewbuddy.domain.model.Coffee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun setCoffee(coffee: Coffee) {
        _uiState.value = _uiState.value.copy(coffee = coffee)
    }

    fun incrementQuantity() {
        val currentQuantity = _uiState.value.quantity
        _uiState.value = _uiState.value.copy(quantity = currentQuantity + 1)
    }

    fun decrementQuantity() {
        val currentQuantity = _uiState.value.quantity
        if (currentQuantity > 1) {
            _uiState.value = _uiState.value.copy(quantity = currentQuantity - 1)
        }
    }

    fun proceedToPayment() {
        val coffee = _uiState.value.coffee
        val quantity = _uiState.value.quantity
        
        if (coffee != null) {
            _uiState.value = _uiState.value.copy(
                shouldNavigateToPayment = true,
                selectedCoffee = coffee,
                finalQuantity = quantity
            )
        }
    }

    fun onNavigatedToPayment() {
        _uiState.value = _uiState.value.copy(shouldNavigateToPayment = false)
    }
}

data class DetailsUiState(
    val coffee: Coffee? = null,
    val quantity: Int = 1,
    val shouldNavigateToPayment: Boolean = false,
    val selectedCoffee: Coffee? = null,
    val finalQuantity: Int = 1
)
