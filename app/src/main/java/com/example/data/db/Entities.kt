package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level_progress")
data class LevelProgressEntity(
    @PrimaryKey val levelId: Int,
    val starsEarned: Int = 0,
    val highScore: Int = 0,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false,
    val completedAt: Long = 0L
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Young Explorer",
    val avatarName: String = "sheru_tiger",
    val coins: Int = 100,
    val totalStars: Int = 0,
    val streakDays: Int = 1,
    val lastPlayedDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "unlocked_badges")
data class UnlockedBadgeEntity(
    @PrimaryKey val badgeId: String,
    val unlockedAt: Long = System.currentTimeMillis()
)
