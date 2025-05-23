package com.example.joiefull.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.joiefull.model.Product
import com.example.joiefull.ui.components.ProductImageCard
import com.example.joiefull.ui.components.ProductListItem

@Composable
fun ClothesDetails(
    onBack: () -> Unit,
    onShare: () -> Unit,
    description: String?,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier =
            modifier
                .fillMaxSize()
                .padding(16.dp),
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .padding(innerPadding)
                //.verticalScroll(rememberScrollState()),
        ) {
            ProductImageCard(
                Product(
                    id = "1",
                    name = "Veste Urbaine",
                    price = 34.0,
                    strikedPrice = 55.1,
                    rate = 0.0,
                    imageUrl = "",
                ),
            )
        }
        ProductListItem()
        Text(description ?: "Hello")
    }
}


@Composable
@Preview(showBackground = true)
fun ClothesDetailsPreview() {
    ClothesDetails(
        onBack = {},
        onShare = {},
        description = "Lorem Ipsum at dominus mundo deus trope mundi, et filiet mundo dominus",
    )
}