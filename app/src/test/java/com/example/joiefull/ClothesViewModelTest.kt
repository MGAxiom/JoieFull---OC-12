package com.example.joiefull

import androidx.lifecycle.viewmodel.compose.viewModel
import app.cash.turbine.test
import com.example.joiefull.data.ClothesRepository
import com.example.joiefull.model.Product
import com.example.joiefull.ui.viewmodel.ClothesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ClothesViewModelTest {
    private lateinit var mockRepository: ClothesRepository
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    val fakeProducts =
        listOf(
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
                isFavorite = false,
            ),
        )

    @Test
    fun `test fetchClothes success after initial error on viewModel`() =
        runTest {
            val initialErrorMessage = "Initial Network Failure"

            coEvery { mockRepository.getAllClothes() } returns Result.failure(Exception(initialErrorMessage))
            val viewModel = ClothesViewModel(mockRepository)

            viewModel.uiState.test {
                assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())

                testDispatcher.scheduler.advanceUntilIdle()
                val errorState = awaitItem()
                assertTrue(errorState is ClothesViewModel.ClothesListUiState.Error)
                assertEquals(initialErrorMessage, (errorState as ClothesViewModel.ClothesListUiState.Error).message)

                coEvery { mockRepository.getAllClothes() } returns Result.success(fakeProducts)

                viewModel.fetchClothes()

                assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())

                testDispatcher.scheduler.advanceUntilIdle()

                assertEquals(ClothesViewModel.ClothesListUiState.Success(fakeProducts), awaitItem())

                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `test initial uiState emits loading`() =
        runTest {
            coEvery { mockRepository.getAllClothes() } returns Result.success(emptyList())

            val viewModel = ClothesViewModel(mockRepository)

            viewModel.uiState.test {
                assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())
            }
        }

    @Test
    fun `test when error on fetchClothes, uiState emits loading then error`() =
        runTest {
            coEvery { mockRepository.getAllClothes() } returns Result.failure(Exception("Error"))

            val viewModel = ClothesViewModel(mockRepository)

            viewModel.uiState.test {
                assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())
                assertEquals(ClothesViewModel.ClothesListUiState.Error("Error"), awaitItem())
            }
        }

    @Test
    fun `test when success on fetchClothes uiState emits loading then success`() =
        runTest {
            coEvery { mockRepository.getAllClothes() } returns Result.success(fakeProducts)

            val viewModel = ClothesViewModel(mockRepository)

            viewModel.uiState.test {
                assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())

                testDispatcher.scheduler.advanceUntilIdle()

                assertEquals(
                    ClothesViewModel.ClothesListUiState.Success(fakeProducts),
                    awaitItem(),
                )
            }
        }

    @Test
    fun `test update product rating`() =
        runTest {
            coEvery { mockRepository.getAllClothes() } returns Result.success(fakeProducts)
            val viewModel = ClothesViewModel(mockRepository)

            viewModel.uiState.test {
                assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())

                testDispatcher.scheduler.advanceUntilIdle()
                // We need to wait for the initial fetch to complete and be successful before updating the rating
                awaitItem() as ClothesViewModel.ClothesListUiState.Success

                viewModel.updateProductRating("a", 5.0f)

                // Now we get the new updatedState with updated rating
                val updatedState = awaitItem() as ClothesViewModel.ClothesListUiState.Success

                assertEquals(5.0f, updatedState.clothes[0].rating)
            }
        }

    @Test
    fun `test update product comment`() =
        runTest {
            coEvery { mockRepository.getAllClothes() } returns Result.success(fakeProducts)

            val viewModel = ClothesViewModel(mockRepository)

            viewModel.uiState.test {
                assertEquals(ClothesViewModel.ClothesListUiState.Loading, awaitItem())

                testDispatcher.scheduler.advanceUntilIdle()
                // We need to wait for the initial fetch to complete and be successful before updating the comment
                awaitItem() as ClothesViewModel.ClothesListUiState.Success

                viewModel.updateProductComment("a", "Super trop cool")

                // Now we get the new updatedState with updated comment
                val updatedState = awaitItem() as ClothesViewModel.ClothesListUiState.Success

                assertEquals("Super trop cool", updatedState.clothes[0].comment)
            }
        }
}
