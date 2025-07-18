package com.example.joiefull.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TitleShimmer() {
    ShimmerComponent(
        Modifier
            .width(200.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.LightGray),
    )
}

@Composable
fun ProductShimmer() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ProductShimmerCard()
        ProductShimmerDescription()
    }
}

@Composable
fun ProductShimmerDescription() {
    Column(
        modifier =
            Modifier
                .height(60.dp)
                .padding(end = 20.dp),
    ) {
        ShimmerComponent(
            Modifier
                .width(180.dp)
                .height(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),
        )

        Spacer(modifier = Modifier.height(12.dp))

        ShimmerComponent(
            Modifier
                .width(100.dp)
                .height(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),
        )
    }
}

@Composable
fun ProductShimmerCard() {
    ShimmerComponent(
        modifier =
            Modifier
                .size(198.dp)
                .background(Color.LightGray, RoundedCornerShape(10.dp)),
    )
}

@Composable
fun ShimmerComponent(
    modifier: Modifier,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
) {
    val shimmerColors =
        listOf(
            Color.White.copy(alpha = 0.3f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 1.0f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.3f),
        )

    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation =
        transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = durationMillis,
                            easing = LinearEasing,
                        ),
                    repeatMode = RepeatMode.Restart,
                ),
            label = "Shimmer loading animation",
        )

    val brush =
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
            end = Offset(x = translateAnimation.value, y = angleOfAxisY),
        )

    Box(
        modifier = modifier,
    ) {
        Spacer(
            modifier =
                Modifier
                    .matchParentSize()
                    .background(brush),
        )
    }
}
