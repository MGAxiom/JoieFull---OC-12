package com.example.joiefull.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.joiefull.R
import com.example.joiefull.model.Product
import com.example.joiefull.ui.components.ProductListItem
import com.example.joiefull.ui.viewmodel.ClothesListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ClothesDetails(
    onBack: () -> Unit,
    onShare: () -> Unit = {},
    productId: String,
    modifier: Modifier = Modifier,
) {
    val viewModel: ClothesListViewModel = koinViewModel()
    val product by viewModel.setProductDetails(productId).collectAsState(initial = null)

    var rating by remember { mutableStateOf(0f) }
    var value by remember { mutableStateOf("") }

    Scaffold(
        modifier =
            modifier
                .fillMaxSize()
                .padding(16.dp),
    ) { innerPadding ->
        ProductDetailContent(
            innerPadding = innerPadding,
            product = product,
            onBack = onBack,
            onShare = onShare,
            textFieldValue = value,
            onTextfieldChanged = { value = it },
            modifier = modifier,
        )
    }
}

@Composable
private fun RatingComponent() {
    var rating by remember { mutableStateOf(3f) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ico_ne_utilisateur_neutre),
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .size(40.dp)
                    .clip(CircleShape),
        )
        StarRatingBar(
            maxStars = 5,
            rating = rating,
            onRatingChanged = {
                rating = it
            },
        )
    }
}

@Composable
fun ProductDetailContent(
    innerPadding: PaddingValues,
    product: Product?,
    onBack: () -> Unit,
    onShare: () -> Unit,
    textFieldValue: String,
    onTextfieldChanged: (String) -> Unit,
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier =
            modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
    ) {
        product?.let {
            ProductListItem(
                isDetails = true,
                onNavigateBack = onBack,
                product = it,
            )
            Text(it.imageDescription)
        }
        RatingComponent()
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Partagez ici vos impressions sur cette pièce",
                    fontSize = 15.sp,
                )
            },
            value = textFieldValue,
            onValueChange = onTextfieldChanged,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
        )
    }
}

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit,
) {
    val density = LocalDensity.current.density
    val starSize = (11f * density).dp
    val starSpacing = (5f * density).dp

    Row(
        modifier =
            Modifier
                .selectableGroup()
                .padding(
                    top = 4.dp,
                    start = 10.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) R.drawable.star_rate else R.drawable.etoile_outlined_joiefull
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0xFFBDBDBD)
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = iconTintColor,
                modifier =
                    Modifier
                        .selectable(
                            selected = isSelected,
                            onClick = {
                                onRatingChanged(i.toFloat())
                            },
                        )
                        .width(starSize)
                        .height(starSize),
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ClothesDetailsPreview() {
    ClothesDetails(
        onBack = {},
        onShare = {},
        productId = "1",
    )
}
