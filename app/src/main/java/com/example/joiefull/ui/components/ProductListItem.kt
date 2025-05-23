package com.example.joiefull.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.joiefull.R
import com.example.joiefull.model.Product

@Composable
internal fun ProductListItem(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(top = 8.dp),
    ) {
        ProductImageCard(
            product =
                Product(
                    id = "1",
                    name = "Veste Urbaine",
                    price = 34.0,
                    strikedPrice = 55.1,
                    rate = 0.0,
                    imageUrl = "",
                ),
            modifier = Modifier,
        )
        ProductDescription(
            product =
                Product(
                    id = "1",
                    name = "Veste Urbaine",
                    price = 0.0,
                    strikedPrice = 0.0,
                    rate = 0.0,
                    imageUrl = "",
                ),
        )
    }
}

@Composable
private fun ProductDescription(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .size(width = 200.dp, 50.dp)
                .padding(start = 8.dp, end = 8.dp),
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = product.name,
                modifier = Modifier.weight(1f),
            )
            Row(
                // horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier,
            ) {
                Image(
                    painterResource(R.drawable.star_rate),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
                Text(product.rate.toString())
            }
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = product.price.toString(),
                modifier = Modifier.weight(1f),
            )
            Text(
                text = product.strikedPrice.toString(),
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview(showBackground = true)
fun ProductListItemPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        ProductListItem()
    }
}

@Composable
@Preview(showBackground = true)
fun ProductDescriptionPreview() {
    ProductDescription(
        product =
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


