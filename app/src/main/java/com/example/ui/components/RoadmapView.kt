package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.db.LevelProgressEntity
import com.example.data.model.LevelData
import com.example.ui.theme.GoldenStar
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.RoyalNavy
import com.example.ui.theme.SaffronPrimary

@Composable
fun RoadmapNodeItem(
    level: LevelData,
    progress: LevelProgressEntity?,
    isNextPlayable: Boolean,
    onLevelSelected: (LevelData) -> Unit,
    modifier: Modifier = Modifier
) {
    val isUnlocked = progress?.isUnlocked == true || level.levelNumber == 1
    val isCompleted = progress?.isCompleted == true
    val starsEarned = progress?.starsEarned ?: 0

    // Pulsing animation for the current next playable level
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_trans")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (isNextPlayable) 1.08f else 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    // Zigzag alignment: even levels align right, odd levels align left
    val isEven = level.levelNumber % 2 == 0

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = if (isEven) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isEven) {
                // Card on left, Node on right
                LevelInfoCard(
                    level = level,
                    isUnlocked = isUnlocked,
                    isCompleted = isCompleted,
                    starsEarned = starsEarned,
                    onTap = { if (isUnlocked) onLevelSelected(level) },
                    modifier = Modifier.weight(1f, fill = false)
                )
                Spacer(modifier = Modifier.width(14.dp))
                LevelCircleButton(
                    level = level,
                    isUnlocked = isUnlocked,
                    isCompleted = isCompleted,
                    isNextPlayable = isNextPlayable,
                    pulseScale = pulseScale,
                    onTap = { if (isUnlocked) onLevelSelected(level) }
                )
            } else {
                // Node on left, Card on right
                LevelCircleButton(
                    level = level,
                    isUnlocked = isUnlocked,
                    isCompleted = isCompleted,
                    isNextPlayable = isNextPlayable,
                    pulseScale = pulseScale,
                    onTap = { if (isUnlocked) onLevelSelected(level) }
                )
                Spacer(modifier = Modifier.width(14.dp))
                LevelInfoCard(
                    level = level,
                    isUnlocked = isUnlocked,
                    isCompleted = isCompleted,
                    starsEarned = starsEarned,
                    onTap = { if (isUnlocked) onLevelSelected(level) },
                    modifier = Modifier.weight(1f, fill = false)
                )
            }
        }

        // Stepping stone connector dots
        if (level.levelNumber < 10) {
            Column(
                modifier = Modifier
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(
                                if (isCompleted) IndiaGreen.copy(alpha = 0.6f)
                                else Color.LightGray.copy(alpha = 0.5f)
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun LevelCircleButton(
    level: LevelData,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    isNextPlayable: Boolean,
    pulseScale: Float,
    onTap: () -> Unit
) {
    val nodeBrush = when {
        isCompleted -> Brush.linearGradient(listOf(IndiaGreen, Color(0xFF2E7D32)))
        isNextPlayable -> Brush.linearGradient(listOf(SaffronPrimary, Color(0xFFFF9100)))
        isUnlocked -> Brush.linearGradient(listOf(RoyalNavy, Color(0xFF3949AB)))
        else -> Brush.linearGradient(listOf(Color(0xFF9E9E9E), Color(0xFF757575)))
    }

    Box(
        modifier = Modifier
            .scale(if (isNextPlayable) pulseScale else 1f)
            .size(76.dp)
            .shadow(
                elevation = if (isNextPlayable) 10.dp else 4.dp,
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(nodeBrush)
            .border(
                width = if (isNextPlayable) 3.5.dp else 2.dp,
                color = if (isNextPlayable) GoldenStar else Color.White,
                shape = CircleShape
            )
            .clickable(enabled = isUnlocked, onClick = onTap)
            .testTag("level_node_${level.levelNumber}"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!isUnlocked) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Locked",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Lvl ${level.levelNumber}",
                    color = Color.White.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 11.sp
                )
            } else {
                Text(
                    text = level.iconEmoji,
                    fontSize = 26.sp
                )
                Text(
                    text = "Lvl ${level.levelNumber}",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun LevelInfoCard(
    level: LevelData,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    starsEarned: Int,
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(230.dp)
            .clickable(enabled = isUnlocked, onClick = onTap)
            .testTag("level_card_${level.levelNumber}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface else Color(0xFFF0F0F0)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUnlocked) 4.dp else 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = level.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray,
                    maxLines = 1
                )

                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Completed",
                        tint = IndiaGreen,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = level.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = if (isUnlocked) MaterialTheme.colorScheme.onSurfaceVariant else Color.LightGray,
                maxLines = 2,
                fontSize = 11.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StarRatingRow(
                    starsEarned = starsEarned,
                    maxStars = 3,
                    starSize = 18.dp
                )

                if (isUnlocked) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isCompleted) "Replay" else "Play",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaffronPrimary
                        )
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
