package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    // Level Progress
    @Query("SELECT * FROM level_progress ORDER BY levelId ASC")
    fun getAllLevelProgress(): Flow<List<LevelProgressEntity>>

    @Query("SELECT * FROM level_progress WHERE levelId = :levelId")
    suspend fun getLevelProgress(levelId: Int): LevelProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateLevel(level: LevelProgressEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInitialLevels(levels: List<LevelProgressEntity>)

    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getUserProfileOnce(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfileEntity)

    // Badges
    @Query("SELECT * FROM unlocked_badges")
    fun getAllUnlockedBadges(): Flow<List<UnlockedBadgeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun unlockBadge(badge: UnlockedBadgeEntity)
}
