package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.db.LevelProgressEntity
import com.example.data.db.UserProfileEntity
import com.example.data.model.LevelData
import com.example.ui.components.AvatarRegistry
import com.example.ui.components.MascotSpeechBubble
import com.example.ui.components.RoadmapNodeItem
import com.example.ui.theme.GoldenStar
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.RoyalNavy
import com.example.ui.theme.SaffronPrimary

@Composable
fun HomeScreen(
    userProfile: UserProfileEntity,
    levels: List<LevelData>,
    levelProgressList: List<LevelProgressEntity>,
    onLevelSelected: (LevelData) -> Unit,
    onDailyChallengeClick: () -> Unit,
    onAvatarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Find next playable level
    val progressMap = levelProgressList.associateBy { it.levelId }
    val nextPlayableLevel = levels.firstOrNull { level ->
        val progress = progressMap[level.levelNumber]
        val isUnlocked = progress?.isUnlocked == true || level.levelNumber == 1
        val isCompleted = progress?.isCompleted == true
        isUnlocked && !isCompleted
    } ?: levels.firstOrNull()

    val avatar = AvatarRegistry.getAvatar(userProfile.avatarName)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("home_screen_list"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // 1. Top Stats Bar
        item {
            TopAppBarStats(
                userProfile = userProfile,
                onAvatarClick = onAvatarClick
            )
        }

        // 2. Hero Banner
        item {
            HeroBannerCard(
                nextPlayableLevel = nextPlayableLevel,
                onPlayNext = {
                    nextPlayableLevel?.let { onLevelSelected(it) }
                }
            )
        }

        // 3. Mascot Encouragement
        item {
            val encouragementMsg = if (userProfile.totalStars > 15) {
                "Incredible job, ${userProfile.name}! You're becoming a true Bharat Master!"
            } else if (userProfile.totalStars > 5) {
                "Look at those stars shine! Let's conquer the next state on the map!"
            } else {
                avatar.greeting
            }

            MascotSpeechBubble(
                avatarId = userProfile.avatarName,
                speechText = encouragementMsg,
                isCheering = true
            )
        }

        // 4. Quick Action Cards (Daily Quiz & Journey info)
        item {
            QuickActionsRow(
                onDailyChallengeClick = onDailyChallengeClick
            )
        }

        // 5. Interactive Roadmap Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Map,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "India Adventure Roadmap",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Text(
                        text = "Follow the winding path from North to South to master all 36 States & UTs!",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // 6. Roadmap Levels
        items(levels) { level ->
            val progress = progressMap[level.levelNumber]
            val isNextPlayable = level.levelNumber == nextPlayableLevel?.levelNumber

            RoadmapNodeItem(
                level = level,
                progress = progress,
                isNextPlayable = isNextPlayable,
                onLevelSelected = onLevelSelected
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TopAppBarStats(
    userProfile: UserProfileEntity,
    onAvatarClick: () -> Unit
) {
    val avatar = AvatarRegistry.getAvatar(userProfile.avatarName)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Player Avatar & Name
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .clickable(onClick = onAvatarClick)
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, SaffronPrimary.copy(alpha = 0.3f), RoundedCornerShape(30.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(avatar.primaryColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = avatar.emoji, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = userProfile.name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = avatar.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = SaffronPrimary,
                    fontSize = 10.sp
                )
            }
        }

        // Coins & Stars Badges
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Coins Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(GoldenYellow.copy(alpha = 0.18f))
                    .border(1.dp, GoldenYellow, RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(text = "🪙", fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${userProfile.coins}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE65100)
                )
            }

            // Stars Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFFFF9C4))
                    .border(1.dp, GoldenStar, RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Stars",
                    tint = GoldenStar,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${userProfile.totalStars}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF57F17)
                )
            }
        }
    }
}

@Composable
private fun HeroBannerCard(
    nextPlayableLevel: LevelData?,
    onPlayNext: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(6.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_india_quiz_hero),
                contentDescription = "India Explorer Banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )

            // Overlay Text and Action
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Bharat Quest 🇮🇳",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Text(
                    text = "Explore 28 States, 8 UTs & Rich Heritage!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (nextPlayableLevel != null) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(SaffronPrimary)
                            .clickable(onClick = onPlayNext)
                            .padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Play Level ${nextPlayableLevel.levelNumber} Now",
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "🚀", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickActionsRow(
    onDailyChallengeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Daily Quick Blitz Card
        Card(
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onDailyChallengeClick)
                .shadow(3.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SaffronPrimary.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = "Daily Quiz",
                        tint = SaffronPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Daily Blitz",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE65100)
                    )
                    Text(
                        text = "Quick 5 Qs (+30 🪙)",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        color = Color(0xFFBF360C)
                    )
                }
            }
        }

        // State Explorer Card
        Card(
            modifier = Modifier
                .weight(1f)
                .shadow(3.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(IndiaGreen.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🗺️", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "28 States",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )
                    Text(
                        text = "8 UTs to Learn",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        color = Color(0xFF2E7D32)
                    )
                }
            }
        }
    }
}
