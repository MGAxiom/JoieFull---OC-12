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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.joiefull.R
import com.example.joiefull.model.Product
import com.example.joiefull.utils.ShareButton

@Composable
fun ProductImageCard(
    product: Product,
    isDetails: Boolean?,
    modifier: Modifier = Modifier,
    onFavorite: (String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Box {
        when (isDetails) {
            true ->
                DetailsCard(
                    onNavigateBack = { onNavigateBack() },
                    imageUrl = product.imageUrl,
                    modifier = modifier,
                    productTextInfos = "${product.name} - ${product.price}€",
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
            isLiked = product.isFavorite,
            onClick = onFavorite,
            id = product.id,
            modifier =
                Modifier
                    .semantics {
                        role = Role.Button
                        contentDescription = "Bouton de like, toucher le bouton pour ${
                            if (!product.isFavorite) "mettre en favoris" else "retirer des favoris"
                        }"
                    }
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
    productTextInfos: String,
    imageUrl: String,
    modifier: Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier =
            modifier.semantics {
                contentDescription = "image $productTextInfos"
            },
    ) {
        Column {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(480.dp),
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier.semantics {
                            contentDescription = "image $productTextInfos"
                        },
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
                    BackButton(
                        onClick = onNavigateBack,
                        modifier =
                            Modifier.semantics {
                                role = Role.Button
                                contentDescription = "Bouton de retour"
                            },
                    )
                    ShareButton(
                        text = productTextInfos,
                        imageUrl = imageUrl,
                        modifier =
                            Modifier.semantics {
                                role = Role.Button
                                contentDescription = "Bouton de partage"
                            },
                    )
                }
            }
        }
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
) {
    IconButton(
        onClick = onClick,
        modifier =
            modifier
                .background(Color.White.copy(alpha = 0.3f), CircleShape)
                .size(36.dp)
                .background(color = backgroundColor, shape = CircleShape)
                .clip(CircleShape),
        // Permet d'éviter d'avoir une ombre carré lors du clic
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Navigate back",
            tint = Color.Black,
            modifier = Modifier.size(15.dp),
        )
    }
}

@Composable
fun CustomPillButton(
    id: String,
    isLiked: Boolean,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.filling_heart_like_animation))
    var isPlaying by remember { mutableStateOf(false) }
    val LocalLottieClipSpec = staticCompositionLocalOf { LottieClipSpec.Progress() }
    val progress by animateLottieCompositionAsState(
        composition,
        clipSpec = if (isLiked) LocalLottieClipSpec.current.copy(0f, 0.6f) else LocalLottieClipSpec.current.copy(0.5f, 1f),
        isPlaying = isPlaying,
        iterations = 1,
    )

    Button(
        onClick = {
            onClick(id)
            isPlaying = true
        },
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
        ) {
            LottieAnimation(
                modifier = Modifier.size(35.dp),
                composition = composition,
                progress = { progress },
            )
            Text(
                if (isLiked) "1" else "0",
            )
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
                category = "Tops",
            ),
            isDetails = false,
            onNavigateBack = {},
            onFavorite = {},
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
            productTextInfos = "Veste Urbaine - 34€",
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
        isLiked = true,
        onClick = {},
        id = "",
    )
}
