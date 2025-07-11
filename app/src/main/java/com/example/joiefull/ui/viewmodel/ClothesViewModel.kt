package com.example.joiefull.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.data.ClothesRepository
import com.example.joiefull.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClothesViewModel(
    private val clothesRepository: ClothesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ClothesListUiState>(ClothesListUiState.Loading)
    val uiState: StateFlow<ClothesListUiState> = _uiState.asStateFlow()

    init {
        fetchClothes()
    }

    fun fetchClothes() {
        viewModelScope.launch {
            _uiState.value = ClothesListUiState.Loading
            clothesRepository.getAllClothes()
                .onSuccess { clothesList ->
                    _uiState.value = ClothesListUiState.Success(clothesList)
                }
                .onFailure { exception ->
                    _uiState.value = ClothesListUiState.Error(exception.message ?: "Unknown error")
                }
        }
    }

    fun updateProductRating(
        productId: String,
        rating: Float,
    ) {
        val currentState = _uiState.value
        if (currentState is ClothesListUiState.Success) {
            val updatedProducts =
                currentState.clothes.map { product ->
                    if (product.id == productId) product.copy(rating = rating) else product
                }
            _uiState.value = ClothesListUiState.Success(updatedProducts)
        }
    }

    fun updateProductComment(
        productId: String,
        comment: String,
    ) {
        val currentState = _uiState.value
        if (currentState is ClothesListUiState.Success) {
            val updatedProducts =
                currentState.clothes.map { product ->
                    if (product.id == productId) product.copy(comment = comment) else product
                }
            _uiState.value = ClothesListUiState.Success(updatedProducts)
        }
    }

    fun toggleProductFavorite(productId: String) {
        val currentState = _uiState.value
        if (currentState is ClothesListUiState.Success) {
            val updatedProducts =
                currentState.clothes.map { product ->
                    if (product.id == productId) product.copy(isFavorite = !product.isFavorite) else product
                }
            _uiState.value = ClothesListUiState.Success(updatedProducts)
        }
    }

    sealed class ClothesListUiState {
        data object Loading : ClothesListUiState()

        data class Success(val clothes: List<Product>) : ClothesListUiState()

        data class Error(val message: String) : ClothesListUiState()
    }
}
