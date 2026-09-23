package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.db.UserProfileEntity
import com.example.data.model.LevelData
import com.example.data.repository.QuizResultSummary
import com.example.ui.components.AvatarRegistry
import com.example.ui.components.ConfettiCelebration
import com.example.ui.components.StarRatingRow
import com.example.ui.theme.GoldenStar
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.RoyalNavy
import com.example.ui.theme.SaffronPrimary

@Composable
fun QuizResultScreen(
    summary: QuizResultSummary?,
    allLevels: List<LevelData>,
    userProfile: UserProfileEntity,
    onNextLevel: (LevelData) -> Unit,
    onRetryLevel: (LevelData) -> Unit,
    onBackToMap: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (summary == null) return

    val currentLevel = allLevels.find { it.levelNumber == summary.levelId }
    val nextLevel = allLevels.find { it.levelNumber == summary.levelId + 1 }
    val avatar = AvatarRegistry.getAvatar(userProfile.avatarName)
    val isWin = summary.starsAwarded > 0

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("quiz_result_screen")
    ) {
        // Confetti celebration if passed!
        if (isWin) {
            ConfettiCelebration()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Cheering Mascot Avatar
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .shadow(8.dp, CircleShape)
                    .clip(CircleShape)
                    .background(avatar.primaryColor.copy(alpha = 0.25f))
                    .border(4.dp, if (isWin) GoldenStar else Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isWin) avatar.emoji else "🤔",
                    fontSize = 48.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Title
            Text(
                text = when (summary.starsAwarded) {
                    3 -> "Superstar! 3 Stars!"
                    2 -> "Great Job, Explorer!"
                    1 -> "Level Cleared!"
                    else -> "Keep Practicing!"
                },
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = if (isWin) SaffronPrimary else Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Text(
                text = currentLevel?.title ?: "Level ${summary.levelId}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Star Rating Display
            StarRatingRow(
                starsEarned = summary.starsAwarded,
                maxStars = 3,
                starSize = 44.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Score & Rewards Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Score readout
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Correct Answers
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${summary.score} / ${summary.total}",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = IndiaGreen
                            )
                            Text(
                                text = "Correct Answers",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Divider
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp)
                                .background(Color.LightGray.copy(alpha = 0.5f))
                        )

                        // Coins Earned
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🪙", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "+${summary.coinsEarned}",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFE65100)
                                )
                            }
                            Text(
                                text = "Coins Won",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Next level unlocked banner
                    if (summary.nextLevelUnlocked && nextLevel != null) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(Color(0xFFE8F5E9), Color(0xFFC8E6C9))
                                    )
                                )
                                .padding(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🔓", fontSize = 22.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "New Level Unlocked!",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1B5E20)
                                    )
                                    Text(
                                        text = "Level ${nextLevel.levelNumber}: ${nextLevel.title}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF2E7D32)
                                    )
                                }
                            }
                        }
                    }

                    // Badges Unlocked
                    if (summary.unlockedBadges.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "🏆 Badges Unlocked!",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = GoldenYellow
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            summary.unlockedBadges.forEach { badge ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = badge.iconEmoji, fontSize = 24.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = badge.title,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = badge.description,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons
            if (isWin && nextLevel != null) {
                Button(
                    onClick = { onNextLevel(nextLevel) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("result_next_level_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Play Level ${nextLevel.levelNumber} Next!",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Retry Button
            if (currentLevel != null) {
                OutlinedButton(
                    onClick = { onRetryLevel(currentLevel) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("result_retry_button"),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        tint = RoyalNavy
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isWin) "Replay for Better Score" else "Try Level Again",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = RoyalNavy
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Back to Map Button
            Button(
                onClick = onBackToMap,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("result_back_map_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = IndiaGreen)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Back to Adventure Map",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
