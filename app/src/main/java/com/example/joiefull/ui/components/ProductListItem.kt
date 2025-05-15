package com.example.joiefull.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.joiefull.model.Product

@Composable
internal fun ProductListItem(
    category: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier
            .size(150.dp)
        )
        Card {}
        ProductDescription(
            product = Product(
                id = "1",
                name = "Veste Urbaine",
                price = 0.0,
                strikedPrice = 0.0,
                rate = 0.0,
                imageUrl = ""
            )
        )
    }
}

@Composable
private fun ProductDescription(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.name,
                modifier = Modifier.weight(1f)
            )
            Row {//Image(Star Icon)
                Text(product.rate.toString())
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductListItemPreview() {
    ProductListItem(category = "Hauts")
}

@Composable
@Preview(showBackground = true)
fun ProductDescriptionPreview() {
    ProductDescription(
        product = Product(
            id = "1",
            name = "Veste Urbaine",
            price = 0.0,
            strikedPrice = 0.0,
            rate = 0.0,
            imageUrl = ""
        )
    )
}
