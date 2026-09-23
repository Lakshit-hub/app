package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.LevelData
import com.example.data.model.QuizRepositoryData
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.AppNavDestination
import com.example.ui.viewmodel.QuizViewModel

@Composable
fun MainAppScaffold(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val levelProgressList by viewModel.levelProgressList.collectAsStateWithLifecycle()
    val unlockedBadges by viewModel.unlockedBadges.collectAsStateWithLifecycle()
    val quizState by viewModel.quizState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedFilterType by viewModel.selectedFilterType.collectAsStateWithLifecycle()
    val selectedZone by viewModel.selectedZone.collectAsStateWithLifecycle()

    // Back handling
    BackHandler(enabled = currentScreen != AppNavDestination.ROADMAP) {
        when (currentScreen) {
            AppNavDestination.QUIZ, AppNavDestination.RESULT -> {
                viewModel.navigateTo(AppNavDestination.ROADMAP)
            }
            AppNavDestination.EXPLORE, AppNavDestination.TROPHIES -> {
                viewModel.navigateTo(AppNavDestination.ROADMAP)
            }
            AppNavDestination.ROADMAP -> {
                // Exit app or default
            }
        }
    }

    val showBottomBar = currentScreen == AppNavDestination.ROADMAP ||
            currentScreen == AppNavDestination.EXPLORE ||
            currentScreen == AppNavDestination.TROPHIES

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .testTag("main_bottom_nav"),
                    tonalElevation = 6.dp,
                    containerColor = Color.White
                ) {
                    // 1. Roadmap Tab
                    NavigationBarItem(
                        selected = currentScreen == AppNavDestination.ROADMAP,
                        onClick = { viewModel.navigateTo(AppNavDestination.ROADMAP) },
                        icon = {
                            Icon(
                                imageVector = if (currentScreen == AppNavDestination.ROADMAP) Icons.Filled.Map else Icons.Outlined.Map,
                                contentDescription = "Roadmap"
                            )
                        },
                        label = { Text("Roadmap") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = SaffronPrimary,
                            selectedTextColor = SaffronPrimary,
                            indicatorColor = SaffronPrimary.copy(alpha = 0.15f)
                        ),
                        modifier = Modifier.testTag("nav_tab_roadmap")
                    )

                    // 2. Explore Tab
                    NavigationBarItem(
                        selected = currentScreen == AppNavDestination.EXPLORE,
                        onClick = { viewModel.navigateTo(AppNavDestination.EXPLORE) },
                        icon = {
                            Icon(
                                imageVector = if (currentScreen == AppNavDestination.EXPLORE) Icons.Filled.Explore else Icons.Outlined.Explore,
                                contentDescription = "Gyan Kosh"
                            )
                        },
                        label = { Text("Gyan Kosh") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = IndiaGreen,
                            selectedTextColor = IndiaGreen,
                            indicatorColor = IndiaGreen.copy(alpha = 0.15f)
                        ),
                        modifier = Modifier.testTag("nav_tab_explore")
                    )

                    // 3. Trophies Tab
                    NavigationBarItem(
                        selected = currentScreen == AppNavDestination.TROPHIES,
                        onClick = { viewModel.navigateTo(AppNavDestination.TROPHIES) },
                        icon = {
                            Icon(
                                imageVector = if (currentScreen == AppNavDestination.TROPHIES) Icons.Filled.EmojiEvents else Icons.Outlined.EmojiEvents,
                                contentDescription = "Rewards"
                            )
                        },
                        label = { Text("Rewards") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = SaffronPrimary,
                            selectedTextColor = SaffronPrimary,
                            indicatorColor = SaffronPrimary.copy(alpha = 0.15f)
                        ),
                        modifier = Modifier.testTag("nav_tab_trophies")
                    )
                }
            }
        }
    ) { innerPadding ->
        when (currentScreen) {
            AppNavDestination.ROADMAP -> {
                HomeScreen(
                    userProfile = userProfile,
                    levels = viewModel.allLevels,
                    levelProgressList = levelProgressList,
                    onLevelSelected = { level ->
                        viewModel.startLevelQuiz(level)
                    },
                    onDailyChallengeClick = {
                        // Generate random 5-question Daily Blitz
                        val randomQuestions = QuizRepositoryData.levels
                            .flatMap { it.questions }
                            .shuffled()
                            .take(5)

                        val dailyLevel = LevelData(
                            levelNumber = 0,
                            title = "Daily India Blitz ⚡",
                            subtitle = "5 random questions across India!",
                            iconEmoji = "⚡",
                            unlockRewardCoins = 40,
                            questions = randomQuestions
                        )
                        viewModel.startLevelQuiz(dailyLevel)
                    },
                    onAvatarClick = {
                        viewModel.navigateTo(AppNavDestination.TROPHIES)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            AppNavDestination.QUIZ -> {
                QuizScreen(
                    quizState = quizState,
                    userProfile = userProfile,
                    onSelectOption = { index ->
                        viewModel.selectOption(index)
                    },
                    onSubmitAnswer = {
                        viewModel.submitAnswer()
                    },
                    onNextQuestion = {
                        viewModel.nextQuestion()
                    },
                    onUse5050 = {
                        viewModel.use5050()
                    },
                    onUseHint = {
                        viewModel.revealHint()
                    },
                    onBack = {
                        viewModel.navigateTo(AppNavDestination.ROADMAP)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            AppNavDestination.RESULT -> {
                QuizResultScreen(
                    summary = quizState.resultSummary,
                    allLevels = viewModel.allLevels,
                    userProfile = userProfile,
                    onNextLevel = { nextLvl ->
                        viewModel.startLevelQuiz(nextLvl)
                    },
                    onRetryLevel = { currLvl ->
                        viewModel.startLevelQuiz(currLvl)
                    },
                    onBackToMap = {
                        viewModel.navigateTo(AppNavDestination.ROADMAP)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            AppNavDestination.EXPLORE -> {
                ExploreScreen(
                    allTerritories = viewModel.allTerritories,
                    searchQuery = searchQuery,
                    selectedType = selectedFilterType,
                    selectedZone = selectedZone,
                    onSearchChange = { query ->
                        viewModel.setSearchQuery(query)
                    },
                    onTypeSelect = { type ->
                        viewModel.setFilterType(type)
                    },
                    onZoneSelect = { zone ->
                        viewModel.setZone(zone)
                    },
                    onStateQuizClick = { state ->
                        viewModel.startStateMiniQuiz(state)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            AppNavDestination.TROPHIES -> {
                TrophyScreen(
                    userProfile = userProfile,
                    allBadges = viewModel.allBadges,
                    unlockedBadgeIds = unlockedBadges,
                    onSelectAvatar = { avatarId ->
                        viewModel.updateAvatar(avatarId)
                    },
                    onUpdateName = { name ->
                        viewModel.updatePlayerName(name)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
