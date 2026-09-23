package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.db.UserProfileEntity
import com.example.data.model.LevelData
import com.example.data.model.QuizQuestion
import com.example.ui.components.AvatarRegistry
import com.example.ui.components.MascotSpeechBubble
import com.example.ui.theme.CoralRed
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.IndiaGreen
import com.example.ui.theme.RoyalNavy
import com.example.ui.theme.SaffronPrimary
import com.example.ui.viewmodel.QuizUiState

@Composable
fun QuizScreen(
    quizState: QuizUiState,
    userProfile: UserProfileEntity,
    onSelectOption: (Int) -> Unit,
    onSubmitAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onUse5050: () -> Unit,
    onUseHint: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val level: LevelData = quizState.level ?: return
    val currentQ: QuizQuestion = level.questions.getOrNull(quizState.currentQuestionIndex) ?: return
    val totalQuestions = level.questions.size
    val progress = (quizState.currentQuestionIndex + 1).toFloat() / totalQuestions.toFloat()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("quiz_screen"),
        topBar = {
            QuizTopBar(
                title = level.title,
                currentIndex = quizState.currentQuestionIndex + 1,
                totalCount = totalQuestions,
                coins = userProfile.coins,
                onBack = onBack
            )
        },
        bottomBar = {
            QuizBottomActionBar(
                isSubmitted = quizState.isSubmitted,
                selectedOption = quizState.selectedOptionIndex,
                onCheck = onSubmitAnswer,
                onNext = onNextQuestion
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Linear Progress Indicator
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = SaffronPrimary,
                trackColor = SaffronPrimary.copy(alpha = 0.2f),
                strokeCap = StrokeCap.Round
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Lifelines Row (50:50 and Hint)
            LifelinesRow(
                is5050Used = quizState.is5050Used,
                isHintRevealed = quizState.isHintRevealed,
                isAnswerSubmitted = quizState.isSubmitted,
                on5050Click = onUse5050,
                onHintClick = onUseHint
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Question Card
            QuestionCard(
                question = currentQ,
                isHintRevealed = quizState.isHintRevealed,
                avatarId = userProfile.avatarName
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Options List
            currentQ.options.forEachIndexed { index, optionText ->
                val isEliminated = quizState.eliminatedOptions.contains(index)
                val isSelected = quizState.selectedOptionIndex == index
                val isCorrect = quizState.isSubmitted && index == currentQ.correctIndex
                val isWrongSelection = quizState.isSubmitted && isSelected && !isCorrect

                OptionButton(
                    optionLetter = ('A' + index).toString(),
                    optionText = optionText,
                    isSelected = isSelected,
                    isCorrect = isCorrect,
                    isWrong = isWrongSelection,
                    isEliminated = isEliminated,
                    isSubmitted = quizState.isSubmitted,
                    onClick = {
                        if (!isEliminated && !quizState.isSubmitted) {
                            onSelectOption(index)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Post-Answer Educational Explanation
            AnimatedVisibility(
                visible = quizState.isSubmitted,
                enter = fadeIn() + slideInVertically(initialOffsetY = { 40 })
            ) {
                ExplanationCard(
                    isCorrect = quizState.isCorrect,
                    explanation = currentQ.explanation,
                    avatarId = userProfile.avatarName
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun QuizTopBar(
    title: String,
    currentIndex: Int,
    totalCount: Int,
    coins: Int,
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag("quiz_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Text(
                    text = "Question $currentIndex of $totalCount",
                    style = MaterialTheme.typography.labelSmall,
                    color = SaffronPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Coins Display
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(GoldenYellow.copy(alpha = 0.2f))
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(text = "🪙", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$coins",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE65100)
            )
        }
    }
}

@Composable
private fun LifelinesRow(
    is5050Used: Boolean,
    isHintRevealed: Boolean,
    isAnswerSubmitted: Boolean,
    on5050Click: () -> Unit,
    onHintClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 50:50 Lifeline Button
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (is5050Used || isAnswerSubmitted) Color.LightGray.copy(alpha = 0.3f)
                    else RoyalNavy.copy(alpha = 0.1f)
                )
                .border(
                    width = 1.dp,
                    color = if (is5050Used || isAnswerSubmitted) Color.Gray.copy(alpha = 0.4f) else RoyalNavy,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable(enabled = !is5050Used && !isAnswerSubmitted, onClick = on5050Click)
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .testTag("lifeline_5050"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "50:50",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.ExtraBold,
                color = if (is5050Used || isAnswerSubmitted) Color.Gray else RoyalNavy
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Hint Lifeline Button
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isHintRevealed || isAnswerSubmitted) Color.LightGray.copy(alpha = 0.3f)
                    else SaffronPrimary.copy(alpha = 0.15f)
                )
                .border(
                    width = 1.dp,
                    color = if (isHintRevealed || isAnswerSubmitted) Color.Gray.copy(alpha = 0.4f) else SaffronPrimary,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable(enabled = !isHintRevealed && !isAnswerSubmitted, onClick = onHintClick)
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .testTag("lifeline_hint"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Lightbulb,
                contentDescription = "Hint",
                tint = if (isHintRevealed || isAnswerSubmitted) Color.Gray else SaffronPrimary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (isHintRevealed) "Hint Shown" else "Mascot Hint",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = if (isHintRevealed || isAnswerSubmitted) Color.Gray else SaffronPrimary
            )
        }
    }
}

@Composable
private fun QuestionCard(
    question: QuizQuestion,
    isHintRevealed: Boolean,
    avatarId: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Category Chip
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SaffronPrimary.copy(alpha = 0.15f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = question.category,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = SaffronPrimary
                    )
                }

                Text(
                    text = question.graphicEmoji,
                    fontSize = 28.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = question.question,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 28.sp
            )

            if (isHintRevealed) {
                Spacer(modifier = Modifier.height(12.dp))
                MascotSpeechBubble(
                    avatarId = avatarId,
                    speechText = "Hint: ${question.hint}"
                )
            }
        }
    }
}

@Composable
private fun OptionButton(
    optionLetter: String,
    optionText: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    isWrong: Boolean,
    isEliminated: Boolean,
    isSubmitted: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isEliminated -> Color.LightGray.copy(alpha = 0.2f)
        isCorrect -> EmeraldGreen
        isWrong -> CoralRed
        isSelected -> RoyalNavy.copy(alpha = 0.08f)
        else -> MaterialTheme.colorScheme.surface
    }

    val borderColor = when {
        isEliminated -> Color.Transparent
        isCorrect -> EmeraldGreen
        isWrong -> CoralRed
        isSelected -> RoyalNavy
        else -> Color.LightGray.copy(alpha = 0.4f)
    }

    val contentColor = when {
        isEliminated -> Color.Gray.copy(alpha = 0.4f)
        isCorrect || isWrong -> Color.White
        isSelected -> RoyalNavy
        else -> MaterialTheme.colorScheme.onSurface
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(if (isEliminated) 0.dp else 2.dp, RoundedCornerShape(16.dp))
            .clickable(enabled = !isEliminated && !isSubmitted, onClick = onClick)
            .testTag("option_$optionLetter"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(borderColor),
            width = if (isSelected || isCorrect || isWrong) 2.dp else 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Letter Circle
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            isCorrect || isWrong -> Color.White.copy(alpha = 0.25f)
                            isSelected -> RoyalNavy
                            else -> Color(0xFFF0F0F0)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = optionLetter,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = when {
                        isCorrect || isWrong -> Color.White
                        isSelected -> Color.White
                        else -> Color(0xFF555555)
                    }
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = optionText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isSelected || isCorrect) FontWeight.Bold else FontWeight.Medium,
                color = contentColor,
                modifier = Modifier.weight(1f)
            )

            if (isCorrect) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Correct",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else if (isWrong) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Incorrect",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun ExplanationCard(
    isCorrect: Boolean,
    explanation: String,
    avatarId: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCorrect) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (isCorrect) "🎉 Shabash! Correct!" else "💡 Learning Moment:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isCorrect) IndiaGreen else Color(0xFFE65100)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = explanation,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF263238),
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun QuizBottomActionBar(
    isSubmitted: Boolean,
    selectedOption: Int?,
    onCheck: () -> Unit,
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        if (!isSubmitted) {
            Button(
                onClick = onCheck,
                enabled = selectedOption != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("submit_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SaffronPrimary,
                    disabledContainerColor = Color.LightGray.copy(alpha = 0.5f)
                )
            ) {
                Text(
                    text = "Check Answer 🌟",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        } else {
            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("next_question_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = IndiaGreen)
            ) {
                Text(
                    text = "Continue Next ➡️",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
