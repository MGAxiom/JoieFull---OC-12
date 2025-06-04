package com.example.joiefull.ui.viewmodel

import com.example.joiefull.data.ClothesRepository
import com.example.joiefull.model.Product
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch


class ClothesListViewModel(
    private val clothesRepository: ClothesRepository
) : ViewModel() {

    private val _uiState = mutableStateOf<ClothesListUiState>(ClothesListUiState.Loading)
    val uiState: State<ClothesListUiState> = _uiState

    init {
        fetchClothes()
    }

    private fun fetchClothes() {
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
}

sealed class ClothesListUiState {
    data object Loading : ClothesListUiState()
    data class Success(val clothes: List<Product>) : ClothesListUiState()
    data class Error(val message: String) : ClothesListUiState()
}