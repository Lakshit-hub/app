package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        LevelProgressEntity::class,
        UserProfileEntity::class,
        UnlockedBadgeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bharat_quiz_db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getInstance(context).appDao()
                                // Prepopulate 10 levels with Level 1 unlocked
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
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
