package com.benki.recipefinder.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IndicatorContainer(
    modifier: Modifier = Modifier,
    currentPage: Int = 0,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        (0..2).forEachIndexed { index, i ->
            val width by animateDpAsState(
                targetValue = if (currentPage == index) 18.dp else 8.dp,
                label = "indicator width $i",
                animationSpec = tween(1000)
            )
            val color by animateColorAsState(
                targetValue = if (currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary,
                label = "indicator color $i",
                animationSpec = tween(1000)
            )
            Box(
                modifier = Modifier
                    .width(width)
                    .height(8.dp)
                    .background(
                        color = color,
                        shape = CircleShape
                    )
                    .clickable { onClick(index) }
            )
        }
    }
}