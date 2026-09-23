package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.random.Random

private data class ConfettiParticle(
    val startX: Float,
    val speedX: Float,
    val speedY: Float,
    val size: Float,
    val color: Color,
    val rotationSpeed: Float,
    val isCircle: Boolean
)

@Composable
fun ConfettiCelebration(
    modifier: Modifier = Modifier,
    particleCount: Int = 45
) {
    val progress = remember { Animatable(0f) }

    val colors = listOf(
        Color(0xFFFF9800), // Saffron
        Color(0xFF4CAF50), // Green
        Color(0xFF2196F3), // Blue
        Color(0xFFFFEB3B), // Yellow
        Color(0xFFE91E63), // Pink
        Color(0xFF9C27B0)  // Purple
    )

    val particles = remember {
        List(particleCount) {
            ConfettiParticle(
                startX = Random.nextFloat(),
                speedX = (Random.nextFloat() - 0.5f) * 0.4f,
                speedY = 0.5f + Random.nextFloat() * 0.7f,
                size = 12f + Random.nextFloat() * 18f,
                color = colors[it % colors.size],
                rotationSpeed = (Random.nextFloat() - 0.5f) * 720f,
                isCircle = Random.nextBoolean()
            )
        }
    }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2800, easing = LinearEasing)
        )
    }

    if (progress.value < 1f) {
        Canvas(modifier = modifier.fillMaxSize()) {
            val canvasW = size.width
            val canvasH = size.height

            particles.forEach { p ->
                val currentY = p.speedY * progress.value * canvasH * 1.2f - 20f
                val currentX = (p.startX + p.speedX * progress.value) * canvasW
                val rotation = p.rotationSpeed * progress.value
                val alpha = (1f - (progress.value * 0.8f)).coerceIn(0f, 1f)

                if (currentY in 0f..canvasH && currentX in 0f..canvasW) {
                    rotate(degrees = rotation, pivot = Offset(currentX, currentY)) {
                        if (p.isCircle) {
                            drawCircle(
                                color = p.color.copy(alpha = alpha),
                                radius = p.size / 2f,
                                center = Offset(currentX, currentY)
                            )
                        } else {
                            drawRect(
                                color = p.color.copy(alpha = alpha),
                                topLeft = Offset(currentX - p.size / 2f, currentY - p.size / 3f),
                                size = Size(p.size, p.size * 0.6f)
                            )
                        }
                    }
                }
            }
        }
    }
}
