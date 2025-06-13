package com.example.joiefull.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.data.ClothesRepository
import com.example.joiefull.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ClothesListViewModel(
    private val clothesRepository: ClothesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ClothesListUiState>(ClothesListUiState.Loading)
    val uiState: StateFlow<ClothesListUiState> = _uiState.asStateFlow()

    private val _allProductsFlow = MutableStateFlow<List<Product>>(emptyList())
    val allProductsFlow: StateFlow<List<Product>> = _allProductsFlow.asStateFlow()

    init {
        fetchClothes()
    }

    private fun fetchClothes() {
        viewModelScope.launch {
            _uiState.value = ClothesListUiState.Loading
            clothesRepository.getAllClothes()
                .onSuccess { clothesList ->
                    _allProductsFlow.value = clothesList
                    _uiState.value = ClothesListUiState.Success(clothesList)
                }
                .onFailure { exception ->
                    _allProductsFlow.value = emptyList()
                    _uiState.value = ClothesListUiState.Error(exception.message ?: "Unknown error")
                }
        }
    }

    fun setProductDetails(productId: String) =
        allProductsFlow.map { list ->
            list.find { it.id == productId }
        }

    fun getClotheId(product: Product): String {
        return product.id
    }
}

sealed class ClothesListUiState {
    data object Loading : ClothesListUiState()

    data class Success(val clothes: List<Product>) : ClothesListUiState()

    data class Error(val message: String) : ClothesListUiState()
}
