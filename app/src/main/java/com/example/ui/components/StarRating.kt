package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.GoldenStar

@Composable
fun StarRatingRow(
    starsEarned: Int,
    maxStars: Int = 3,
    starSize: Dp = 24.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            if (i <= starsEarned) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star $i",
                    tint = GoldenStar,
                    modifier = Modifier.size(starSize)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Empty Star $i",
                    tint = Color.LightGray.copy(alpha = 0.6f),
                    modifier = Modifier.size(starSize)
                )
            }
        }
    }
}
