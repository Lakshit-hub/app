package com.example.data.repository

import com.example.data.db.AppDao
import com.example.data.db.LevelProgressEntity
import com.example.data.db.UnlockedBadgeEntity
import com.example.data.db.UserProfileEntity
import com.example.data.model.BadgeDefinition
import com.example.data.model.IndiaDataStore
import com.example.data.model.IndiaState
import com.example.data.model.LevelData
import com.example.data.model.QuizRepositoryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizRepository(private val dao: AppDao) {

    val allStatesAndUTs: List<IndiaState> = IndiaDataStore.allTerritories

    val allLevels: List<LevelData> = QuizRepositoryData.levels

    val allBadgeDefinitions: List<BadgeDefinition> = QuizRepositoryData.badges

    val levelProgressFlow: Flow<List<LevelProgressEntity>> = dao.getAllLevelProgress()

    val userProfileFlow: Flow<UserProfileEntity> = dao.getUserProfile().map { profile ->
        profile ?: UserProfileEntity()
    }

    val unlockedBadgesFlow: Flow<Set<String>> = dao.getAllUnlockedBadges().map { list ->
        list.map { it.badgeId }.toSet()
    }

    suspend fun ensureInitialData() {
        val existing = dao.getUserProfileOnce()
        if (existing == null) {
            val initialLevels = (1..10).map { level ->
                LevelProgressEntity(
                    levelId = level,
                    starsEarned = 0,
                    highScore = 0,
                    isUnlocked = (level == 1),
                    isCompleted = false
                )
            }
            dao.insertInitialLevels(initialLevels)
            dao.insertOrUpdateProfile(UserProfileEntity())
        }
    }

    suspend fun saveQuizResult(
        levelId: Int,
        correctCount: Int,
        totalQuestions: Int
    ): QuizResultSummary {
        val percentage = (correctCount.toFloat() / totalQuestions.toFloat()) * 100f
        val stars = when {
            percentage >= 80f -> 3
            percentage >= 60f -> 2
            percentage >= 40f -> 1
            else -> 0
        }

        val levelData = allLevels.find { it.levelNumber == levelId }
        val currentProgress = dao.getLevelProgress(levelId)
        val oldStars = currentProgress?.starsEarned ?: 0
        val oldHighScore = currentProgress?.highScore ?: 0

        val newStars = maxOf(oldStars, stars)
        val newHighScore = maxOf(oldHighScore, correctCount)
        val isFirstTimeComplete = (currentProgress?.isCompleted != true) && (stars > 0)

        // Update current level
        val updatedLevel = LevelProgressEntity(
            levelId = levelId,
            starsEarned = newStars,
            highScore = newHighScore,
            isUnlocked = true,
            isCompleted = (currentProgress?.isCompleted == true) || (stars > 0),
            completedAt = System.currentTimeMillis()
        )
        dao.insertOrUpdateLevel(updatedLevel)

        // Unlock next level if this level was cleared
        var nextLevelUnlocked = false
        if (stars > 0 && levelId < allLevels.size) {
            val nextLevelId = levelId + 1
            val nextLevel = dao.getLevelProgress(nextLevelId)
            if (nextLevel == null || !nextLevel.isUnlocked) {
                dao.insertOrUpdateLevel(
                    nextLevel?.copy(isUnlocked = true)
                        ?: LevelProgressEntity(levelId = nextLevelId, isUnlocked = true)
                )
                nextLevelUnlocked = true
            }
        }

        // Coins earned: Base reward + 10 coins per correct answer + 20 bonus for 3 stars
        val baseCoins = if (isFirstTimeComplete) (levelData?.unlockRewardCoins ?: 50) else 15
        val correctCoins = correctCount * 10
        val starBonus = if (stars == 3) 30 else if (stars == 2) 15 else 0
        val earnedCoins = baseCoins + correctCoins + starBonus

        // Update User Profile
        val currentProfile = dao.getUserProfileOnce() ?: UserProfileEntity()
        val additionalStars = maxOf(0, newStars - oldStars)
        val newTotalStars = currentProfile.totalStars + additionalStars
        val newTotalCoins = currentProfile.coins + earnedCoins

        dao.insertOrUpdateProfile(
            currentProfile.copy(
                coins = newTotalCoins,
                totalStars = newTotalStars,
                lastPlayedDate = System.currentTimeMillis()
            )
        )

        // Check and grant badges
        val newlyUnlockedBadges = mutableListOf<BadgeDefinition>()
        for (badge in allBadgeDefinitions) {
            val shouldUnlock = when {
                badge.requiredLevel > 0 && badge.requiredLevel == levelId && stars > 0 -> true
                badge.requiredStars > 0 && newTotalStars >= badge.requiredStars -> true
                else -> false
            }

            if (shouldUnlock) {
                dao.unlockBadge(UnlockedBadgeEntity(badgeId = badge.id))
                newlyUnlockedBadges.add(badge)
            }
        }

        return QuizResultSummary(
            levelId = levelId,
            score = correctCount,
            total = totalQuestions,
            starsAwarded = stars,
            coinsEarned = earnedCoins,
            nextLevelUnlocked = nextLevelUnlocked,
            unlockedBadges = newlyUnlockedBadges
        )
    }

    suspend fun updateAvatar(avatarName: String) {
        val profile = dao.getUserProfileOnce() ?: UserProfileEntity()
        dao.insertOrUpdateProfile(profile.copy(avatarName = avatarName))
    }

    suspend fun updatePlayerName(newName: String) {
        val profile = dao.getUserProfileOnce() ?: UserProfileEntity()
        dao.insertOrUpdateProfile(profile.copy(name = newName.trim()))
    }
}

data class QuizResultSummary(
    val levelId: Int,
    val score: Int,
    val total: Int,
    val starsAwarded: Int,
    val coinsEarned: Int,
    val nextLevelUnlocked: Boolean,
    val unlockedBadges: List<BadgeDefinition>
)
