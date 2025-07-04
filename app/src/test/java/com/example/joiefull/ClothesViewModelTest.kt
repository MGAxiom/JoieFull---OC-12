package com.example.joiefull

import androidx.lifecycle.viewmodel.compose.viewModel
import app.cash.turbine.test
import com.example.joiefull.data.ClothesRepository
import com.example.joiefull.model.Product
import com.example.joiefull.ui.viewmodel.ClothesViewModel
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ClothesViewModelTest {

    private lateinit var mockRepository: ClothesRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = mockk()
    }

    val fakeProducts = listOf(
        Product(
            price = 1.1,
            id = "a",
            name = "Chaussure",
            strikedPrice = 3.1,
            rate = 4.1,
            imageUrl = "",
            imageDescription = "",
            category = "Accessories",
            rating = 4.1f,
            comment = "Super cool",
            isFavorite = false
        )
    )

    @Test
    fun `test initial uiState emits loading`() = runTest {
        coEvery { mockRepository.getAllClothes() } returns Result.success(emptyList())
        
        val viewModel = ClothesViewModel(mockRepository)

        viewModel.uiState.test {
            assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())
        }
    }


    @Test
    fun `test when error on fetchClothes, uiState emits loading then error`() = runTest {
        coEvery { mockRepository.getAllClothes() } returns Result.failure(Exception("Error"))

        val viewModel = ClothesViewModel(mockRepository)

        viewModel.uiState.test {
            assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())
            assertEquals(ClothesViewModel.ClothesListUiState.Error("Error"), awaitItem())
        }
    }


    @Test
    fun `test when success on fetchClothes uiState emits loading then success`() = runTest {
        coEvery { mockRepository.getAllClothes() } returns Result.success(fakeProducts)

        val viewModel = ClothesViewModel(mockRepository)

        viewModel.uiState.test {
            assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())

            testDispatcher.scheduler.advanceUntilIdle()

            assertEquals(
                ClothesViewModel.ClothesListUiState.Success(fakeProducts),
                awaitItem()
            )
        }
    }
}