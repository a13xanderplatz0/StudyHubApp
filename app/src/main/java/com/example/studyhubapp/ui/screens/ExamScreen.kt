package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studyhubapp.models.Exam
import com.example.studyhubapp.models.Question
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamScreen(
    exam: Exam,
    onExamComplete: (List<Int>) -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswers by remember { mutableStateOf(List(exam.questions.size) { -1 }) }
    var timeRemaining by remember { mutableStateOf(exam.duration * 60) } // Convertir a segundos
    var isExamComplete by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (timeRemaining > 0 && !isExamComplete) {
            delay(1000)
            timeRemaining--
        }
        if (timeRemaining <= 0) {
            isExamComplete = true
            onExamComplete(selectedAnswers)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Examen: ${exam.title}") },
                actions = {
                    Text(
                        text = "${timeRemaining / 60}:${String.format("%02d", timeRemaining % 60)}",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (!isExamComplete) {
                // Barra de progreso
                LinearProgressIndicator(
                    progress = (currentQuestionIndex + 1).toFloat() / exam.questions.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Pregunta actual
                QuestionCard(
                    question = exam.questions[currentQuestionIndex],
                    selectedAnswer = selectedAnswers[currentQuestionIndex],
                    onAnswerSelected = { answerIndex ->
                        selectedAnswers = selectedAnswers.toMutableList().apply {
                            set(currentQuestionIndex, answerIndex)
                        }
                    }
                )

                // Botones de navegación
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { currentQuestionIndex-- },
                        enabled = currentQuestionIndex > 0
                    ) {
                        Text("Anterior")
                    }

                    if (currentQuestionIndex < exam.questions.size - 1) {
                        Button(
                            onClick = { currentQuestionIndex++ }
                        ) {
                            Text("Siguiente")
                        }
                    } else {
                        Button(
                            onClick = {
                                isExamComplete = true
                                onExamComplete(selectedAnswers)
                            }
                        ) {
                            Text("Finalizar")
                        }
                    }
                }
            } else {
                ExamCompleteScreen(
                    exam = exam,
                    answers = selectedAnswers
                )
            }
        }
    }
}

@Composable
fun QuestionCard(
    question: Question,
    selectedAnswer: Int,
    onAnswerSelected: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = question.text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            question.options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedAnswer == index,
                        onClick = { onAnswerSelected(index) }
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ExamCompleteScreen(
    exam: Exam,
    answers: List<Int>
) {
    val correctAnswers = answers.zip(exam.questions).count { (answer, question) ->
        answer == question.correctAnswer
    }
    val score = (correctAnswers.toFloat() / exam.questions.size) * 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Examen Completado!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Puntuación: ${String.format("%.1f", score)}%",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Respuestas correctas: $correctAnswers de ${exam.questions.size}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
} 