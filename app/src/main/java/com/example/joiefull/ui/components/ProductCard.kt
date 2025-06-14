package com.example.joiefull.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.joiefull.R
import com.example.joiefull.model.Category
import com.example.joiefull.model.Product

@Composable
fun ProductImageCard(
    product: Product,
    isDetails: Boolean?,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onShare: () -> Unit,
) {
    Box {
        when (isDetails) {
            true ->
                DetailsCard(
                    onNavigateBack = { onNavigateBack() },
                    onShare = {},
                    imageUrl = product.imageUrl,
                    modifier = modifier,
                )
            false ->
                ListCard(
                    productImgUrl = product.imageUrl,
                    productImgDescription = product.imageDescription,
                    modifier = modifier,
                )
            null -> {}
        }
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
fun ListCard(
    productImgUrl: String,
    productImgDescription: String,
    modifier: Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
    ) {
        AsyncImage(
            model = productImgUrl,
            contentDescription = productImgDescription,
            contentScale = ContentScale.FillWidth,
            modifier =
                Modifier
                    .size(198.dp),
        )
    }
}

@Composable
fun DetailsCard(
    onNavigateBack: () -> Unit = {},
    onShare: () -> Unit = {},
    imageUrl: String,
    scrimColor: Color = Color.White.copy(alpha = 0.3f),
    modifier: Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.large,
    ) {
        Column {
            Box(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .height(480.dp),
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier,
                )
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 2.dp,
                                vertical = 2.dp,
                            )
                            .align(Alignment.TopCenter),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SmallIconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.background(scrimColor, CircleShape),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back",
                            tint = Color.Black,
                            modifier = Modifier.size(15.dp),
                        )
                    }
                    SmallIconButton(
                        onClick = onShare,
                        modifier = Modifier.background(scrimColor, CircleShape),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.outline_share_24),
                            contentDescription = "Navigate back",
                            tint = Color.Black,
                            modifier = Modifier.size(15.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SmallIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier =
            modifier
                .size(36.dp)
                .background(color = backgroundColor, shape = CircleShape)
                .clip(CircleShape),
        // This makes sure that the ripple effect stays within the circle
    ) {
        content()
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
                end = 10.dp,
                top = 2.dp,
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
                painter = painterResource(R.drawable.outline_favorite_border_24),
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
                imageDescription = "",
                category = Category.TOPS,
            ),
            isDetails = false,
            onNavigateBack = {},
            onShare = {},
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun DetailsCardPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        DetailsCard(
            imageUrl =
                "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/" +
                    "D-velopper-une-interface-accessible-en-Jetpack-Compose/" +
                    "main/img/accessories/1.jpg",
            modifier = Modifier,
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun ListCardPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        ListCard(
            productImgUrl =
                "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/" +
                    "D-velopper-une-interface-accessible-en-Jetpack-Compose/" +
                    "main/img/accessories/1.jpg",
            productImgDescription = "",
            modifier = Modifier,
        )
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
