package com.example.joiefull.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.joiefull.model.Product

@Composable
fun ProductImageCard(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Box {
        CustomCard()
        CustomPillButton(
            text = "0",
            onClick = {},
            modifier =
                Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.BottomEnd),
        )
    }
}

@Composable
fun CustomCard() {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        AsyncImage(
            model = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/" +
                    "D-velopper-une-interface-accessible-en-Jetpack-Compose/" +
                    "main/img/accessories/1.jpg",
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier =
                Modifier
                    .size(198.dp),
        )
    }
}

@Composable
fun CustomPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 8.dp),
        contentPadding =
            PaddingValues(
                start = 8.dp,
                end = 10.dp, // Less horizontal padding
                top = 2.dp, // Less vertical padding
                bottom = 2.dp,
            ),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
            ),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Icon(
                Icons.Outlined.Favorite,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
            )
            Text(text)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun CustomImageCardPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        ProductImageCard(
            Product(
                id = "1",
                name = "Veste Urbaine",
                price = 34.0,
                strikedPrice = 55.1,
                rate = 0.0,
                imageUrl = "",
            )
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun CustomCardPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        CustomCard()
    }
}

@Composable
@Preview
fun CustomPillButtonPreview() {
    CustomPillButton(
        "0",
        onClick = {},
    )
}