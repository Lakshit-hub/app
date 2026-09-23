package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SaffronPrimary

data class AvatarInfo(
    val id: String,
    val name: String,
    val title: String,
    val emoji: String,
    val greeting: String,
    val primaryColor: Color
)

object AvatarRegistry {
    val avatars = listOf(
        AvatarInfo(
            id = "sheru_tiger",
            name = "Sheru",
            title = "The Brave Tiger",
            emoji = "🐯",
            greeting = "Roar! Shabash! Let's explore incredible Bharat!",
            primaryColor = Color(0xFFFF9800)
        ),
        AvatarInfo(
            id = "gajju_elephant",
            name = "Gajju",
            title = "The Wise Elephant",
            emoji = "🐘",
            greeting = "Trumpeting with joy! Ready for India's wonders?",
            primaryColor = Color(0xFF607D8B)
        ),
        AvatarInfo(
            id = "mayur_peacock",
            name = "Mayur",
            title = "The Royal Peacock",
            emoji = "🦚",
            greeting = "Spreading colorful feathers of wisdom across India!",
            primaryColor = Color(0xFF00897B)
        ),
        AvatarInfo(
            id = "diya_explorer",
            name = "Diya",
            title = "Star Explorer",
            emoji = "👧",
            greeting = "Namaste explorer! Which state shall we learn today?",
            primaryColor = Color(0xFFE91E63)
        ),
        AvatarInfo(
            id = "kabir_adventurer",
            name = "Kabir",
            title = "Sky Adventurer",
            emoji = "👦",
            greeting = "Ready for the big quiz journey? Let's win gold stars!",
            primaryColor = Color(0xFF3F51B5)
        )
    )

    fun getAvatar(id: String): AvatarInfo {
        return avatars.find { it.id == id } ?: avatars.first()
    }
}

@Composable
fun MascotSpeechBubble(
    avatarId: String,
    speechText: String,
    modifier: Modifier = Modifier,
    isCheering: Boolean = false
) {
    val avatar = AvatarRegistry.getAvatar(avatarId)

    // Gentle bounce animation
    val infiniteTransition = rememberInfiniteTransition(label = "mascot_bounce")
    val bounceOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isCheering) -8f else -4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounce"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Mascot Circle
        Box(
            modifier = Modifier
                .offset(y = bounceOffset.dp)
                .size(60.dp)
                .shadow(elevation = 6.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(avatar.primaryColor.copy(alpha = 0.2f))
                .border(width = 3.dp, color = avatar.primaryColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = avatar.emoji,
                fontSize = 32.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Speech bubble
        Box(
            modifier = Modifier
                .weight(1f)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(topStart = 4.dp, topEnd = 20.dp, bottomEnd = 20.dp, bottomStart = 20.dp))
                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 20.dp, bottomEnd = 20.dp, bottomStart = 20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(
                    width = 1.5.dp,
                    color = avatar.primaryColor.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(topStart = 4.dp, topEnd = 20.dp, bottomEnd = 20.dp, bottomStart = 20.dp)
                )
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Column {
                Text(
                    text = "${avatar.name} says:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = SaffronPrimary
                )
                Text(
                    text = speechText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
