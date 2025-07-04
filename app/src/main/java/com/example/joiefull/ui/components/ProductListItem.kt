package com.example.joiefull.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.joiefull.R
import com.example.joiefull.model.Product
import kotlin.math.roundToInt

@Composable
internal fun ProductListItem(
    isDetails: Boolean,
    product: Product,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onFavorite: (id: String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
            modifier
                .padding(top = 8.dp),
    ) {
        ProductImageCard(
            product = product,
            modifier = Modifier,
            isDetails = isDetails,
            onNavigateBack = { onNavigateBack() },
            onFavorite = onFavorite,
        )
        ProductDescription(
            product = product,
            isDetails = isDetails,
        )
    }
}

@Composable
private fun ProductDescription(
    product: Product,
    isDetails: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .then(
                    if (!isDetails) {
                        Modifier.size(
                            width = 200.dp,
                            50.dp,
                        )
                    } else {
                        Modifier.fillMaxWidth()
                    },
                )
                .padding(start = 8.dp, end = 8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = if (isDetails) 20.sp else 15.sp,
                maxLines = 1,
                modifier = Modifier.weight(1f),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painterResource(R.drawable.star_rate),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 2.dp),
                )
                Text(product.rate.roundToInt().toString())
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = product.price.roundToInt().toString() + "€",
                modifier = Modifier.weight(1f),
            )
            Text(
                text = product.strikedPrice.roundToInt().toString() + "€",
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview(showBackground = true)
fun ProductDetailsItemPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        ProductListItem(
            isDetails = true,
            onNavigateBack = {},
            product =
                Product(
                    id = "1",
                    name = "Veste Urbaine",
                    price = 34.0,
                    strikedPrice = 55.1,
                    rate = 0.0,
                    imageUrl = "",
                    category = "Tops",
                    imageDescription = "",
                    rating = 0f,
                    comment = "",
                    isFavorite = false,
                ),
            onFavorite = {},
        )
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
        ProductListItem(
            isDetails = false,
            onNavigateBack = {},
            product =
                Product(
                    id = "1",
                    name = "Veste Urbaine",
                    price = 34.0,
                    strikedPrice = 55.1,
                    rate = 0.0,
                    imageUrl = "",
                    category = "Tops",
                    imageDescription = "",
                    rating = 0f,
                    comment = "",
                    isFavorite = false,
                ),
            onFavorite = {},
        )
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
                category = "Tops",
                imageDescription = "",
            ),
        isDetails = false,
    )
}
