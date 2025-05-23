package com.example.joiefull.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.joiefull.ui.components.ProductListItem

@Composable
fun ClothesListScreen(
    onClotheTap: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val clothesCategory = listOf("Hauts", "Bas", "Sacs")
    Scaffold(
        modifier =
            modifier
                .fillMaxSize()
                .padding(16.dp),
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(innerPadding),
        ) {
            items(items = clothesCategory, itemContent = { category ->
                Text(
                    text = category,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    repeat(10) {
                        ProductListItem(
                            modifier = modifier.fillMaxWidth(),
                        )
                    }
                }
            })
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewScreenSizes
fun ClothesListPreview() {
    ClothesListScreen(
        onClotheTap = {},
    )
}
