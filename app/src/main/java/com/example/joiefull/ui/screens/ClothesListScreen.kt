package com.example.joiefull.ui.screens

import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.joiefull.model.Product
import com.example.joiefull.ui.components.ProductListItem
import com.example.joiefull.ui.components.ProductShimmer
import com.example.joiefull.ui.components.TitleShimmer
import com.example.joiefull.ui.viewmodel.ClothesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ClothesListScreen(
    onClotheTap: (productId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ClothesViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier =
            modifier
                .fillMaxSize()
                .padding(top = 16.dp),
    ) { innerPadding ->
        when (val state = uiState) {
            is ClothesViewModel.ClothesListUiState.Error -> RetryComponent(
                onClick = { viewModel.fetchClothes() },
                modifier = modifier
            )
            is ClothesViewModel.ClothesListUiState.Loading -> {
                ShimmeringPlaceholders(
                    modifier = modifier,
                    innerPadding = innerPadding,
                )
            }

            is ClothesViewModel.ClothesListUiState.Success -> {
                GroupedProductList(
                    products = state.clothes.groupBy { it.category },
                    onClotheTap = {
                        val selectedProductId = it.id
                        onClotheTap(selectedProductId)
                    },
                    innerPadding = innerPadding,
                    isFavorite = { productId ->
                        viewModel.toggleProductFavorite(productId)
                    },
                )
            }
        }
    }
}

@Composable
fun GroupedProductList(
    products: Map<String, List<Product>>,
    onClotheTap: (product: Product) -> Unit,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    isFavorite: (id: String) -> Unit,
) {
    val categoriesList = products.entries.toList()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier =
            modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding(),
            ),
    ) {
        items(items = categoriesList, key = { it.key }) { (category, categorizedProducts) ->
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = category.replaceFirstChar { it.uppercase() },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                ) {
                    categorizedProducts.forEach { product ->
                        ProductListItem(
                            isDetails = false,
                            product = product,
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onClotheTap(product)
                                    },
                            onNavigateBack = {},
                            onFavorite = isFavorite,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RetryComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Quelque chose s'est mal passé, veuillez réessayer")
        Button(onClick = { onClick() }) {
            Text("Réessayer")
        }
    }
}

@Composable
fun ShimmeringPlaceholders(
    modifier: Modifier,
    innerPadding: PaddingValues,
) {
    val numberOfPlaceholder = 3

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier =
            modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding(),
            ),
    ) {
        items(numberOfPlaceholder) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                TitleShimmer()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier =
                        Modifier
                            .padding(top = 6.dp)
                            .horizontalScroll(rememberScrollState()),
                ) {
                    repeat(3) {
                        ProductShimmer()
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewScreenSizes
fun ClothesListPreview() {
    ClothesListScreen(
        onClotheTap = {},
        viewModel = koinViewModel(),
    )
}
