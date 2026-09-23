package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.db.LevelProgressEntity
import com.example.data.db.UserProfileEntity
import com.example.data.model.BadgeDefinition
import com.example.data.model.IndiaDataStore
import com.example.data.model.IndiaState
import com.example.data.model.IndiaZone
import com.example.data.model.LevelData
import com.example.data.model.QuizQuestion
import com.example.data.model.TerritoryType
import com.example.data.repository.QuizRepository
import com.example.data.repository.QuizResultSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppNavDestination {
    ROADMAP,
    QUIZ,
    RESULT,
    EXPLORE,
    TROPHIES
}

data class QuizUiState(
    val level: LevelData? = null,
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isSubmitted: Boolean = false,
    val isCorrect: Boolean = false,
    val score: Int = 0,
    val is5050Used: Boolean = false,
    val eliminatedOptions: Set<Int> = emptySet(),
    val isHintRevealed: Boolean = false,
    val isFinished: Boolean = false,
    val resultSummary: QuizResultSummary? = null
)

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository

    val userProfile: StateFlow<UserProfileEntity>
    val levelProgressList: StateFlow<List<LevelProgressEntity>>
    val unlockedBadges: StateFlow<Set<String>>

    private val _currentScreen = MutableStateFlow(AppNavDestination.ROADMAP)
    val currentScreen: StateFlow<AppNavDestination> = _currentScreen.asStateFlow()

    private val _quizState = MutableStateFlow(QuizUiState())
    val quizState: StateFlow<QuizUiState> = _quizState.asStateFlow()

    // Explore screen search & filter
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedFilterType = MutableStateFlow<TerritoryType?>(null)
    val selectedFilterType: StateFlow<TerritoryType?> = _selectedFilterType.asStateFlow()

    private val _selectedZone = MutableStateFlow<IndiaZone?>(null)
    val selectedZone: StateFlow<IndiaZone?> = _selectedZone.asStateFlow()

    init {
        val db = AppDatabase.getInstance(application)
        repository = QuizRepository(db.appDao())

        userProfile = repository.userProfileFlow.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            UserProfileEntity()
        )

        levelProgressList = repository.levelProgressFlow.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

        unlockedBadges = repository.unlockedBadgesFlow.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptySet()
        )

        viewModelScope.launch {
            repository.ensureInitialData()
        }
    }

    val allLevels: List<LevelData> get() = repository.allLevels
    val allBadges: List<BadgeDefinition> get() = repository.allBadgeDefinitions
    val allTerritories: List<IndiaState> get() = repository.allStatesAndUTs

    fun navigateTo(destination: AppNavDestination) {
        _currentScreen.value = destination
    }

    // Start a level quiz
    fun startLevelQuiz(level: LevelData) {
        _quizState.value = QuizUiState(
            level = level,
            currentQuestionIndex = 0,
            selectedOptionIndex = null,
            isSubmitted = false,
            isCorrect = false,
            score = 0,
            is5050Used = false,
            eliminatedOptions = emptySet(),
            isHintRevealed = false,
            isFinished = false,
            resultSummary = null
        )
        _currentScreen.value = AppNavDestination.QUIZ
    }

    // Start a mini quiz on a specific state
    fun startStateMiniQuiz(state: IndiaState) {
        val question1 = QuizQuestion(
            id = "sq_cap_${state.id}",
            category = "State Capital",
            question = "What is the capital city of ${state.name}?",
            options = generateOptionsForState(state),
            correctIndex = 0, // Options will be shuffled below
            explanation = "${state.capital} is the capital of ${state.name}! ${state.funFact}",
            hint = "Look at the first letter: starts with '${state.capital.first()}'",
            graphicEmoji = state.emojiIcon
        )

        // Shuffle options and find new correct index
        val shuffledOptions = question1.options.shuffled()
        val correctIndex = shuffledOptions.indexOf(state.capital)

        val miniLevel = LevelData(
            levelNumber = 1,
            title = state.name + " Quiz",
            subtitle = "Mini test on " + state.name,
            iconEmoji = state.emojiIcon,
            unlockRewardCoins = 25,
            questions = listOf(
                question1.copy(
                    options = shuffledOptions,
                    correctIndex = correctIndex
                )
            )
        )

        startLevelQuiz(miniLevel)
    }

    private fun generateOptionsForState(targetState: IndiaState): List<String> {
        val otherCapitals = allTerritories
            .filter { it.capital != targetState.capital }
            .map { it.capital }
            .shuffled()
            .take(3)
        return listOf(targetState.capital) + otherCapitals
    }

    fun selectOption(index: Int) {
        val current = _quizState.value
        if (current.isSubmitted) return
        _quizState.value = current.copy(selectedOptionIndex = index)
    }

    fun submitAnswer() {
        val current = _quizState.value
        val level = current.level ?: return
        val currentQ = level.questions.getOrNull(current.currentQuestionIndex) ?: return
        val selectedIdx = current.selectedOptionIndex ?: return

        val isRight = (selectedIdx == currentQ.correctIndex)
        val newScore = if (isRight) current.score + 1 else current.score

        _quizState.value = current.copy(
            isSubmitted = true,
            isCorrect = isRight,
            score = newScore
        )
    }

    fun nextQuestion() {
        val current = _quizState.value
        val level = current.level ?: return
        val nextIdx = current.currentQuestionIndex + 1

        if (nextIdx < level.questions.size) {
            _quizState.value = current.copy(
                currentQuestionIndex = nextIdx,
                selectedOptionIndex = null,
                isSubmitted = false,
                isCorrect = false,
                is5050Used = false,
                eliminatedOptions = emptySet(),
                isHintRevealed = false
            )
        } else {
            // Level completed! Save results to DB
            finishQuiz(level, current.score, level.questions.size)
        }
    }

    private fun finishQuiz(level: LevelData, score: Int, total: Int) {
        viewModelScope.launch {
            val summary = repository.saveQuizResult(
                levelId = level.levelNumber,
                correctCount = score,
                totalQuestions = total
            )
            _quizState.value = _quizState.value.copy(
                isFinished = true,
                resultSummary = summary
            )
            _currentScreen.value = AppNavDestination.RESULT
        }
    }

    // Lifeline: 50:50
    fun use5050() {
        val current = _quizState.value
        if (current.is5050Used || current.isSubmitted) return
        val level = current.level ?: return
        val currentQ = level.questions.getOrNull(current.currentQuestionIndex) ?: return

        val wrongIndices = currentQ.options.indices.filter { it != currentQ.correctIndex }.shuffled()
        val eliminated = wrongIndices.take(2).toSet()

        _quizState.value = current.copy(
            is5050Used = true,
            eliminatedOptions = eliminated
        )
    }

    // Lifeline: Reveal Hint
    fun revealHint() {
        _quizState.value = _quizState.value.copy(isHintRevealed = true)
    }

    // Profile updates
    fun updateAvatar(avatarId: String) {
        viewModelScope.launch {
            repository.updateAvatar(avatarId)
        }
    }

    fun updatePlayerName(name: String) {
        viewModelScope.launch {
            repository.updatePlayerName(name)
        }
    }

    // Search and filters for Explore
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setFilterType(type: TerritoryType?) {
        _selectedFilterType.value = type
    }

    fun setZone(zone: IndiaZone?) {
        _selectedZone.value = zone
    }
}
