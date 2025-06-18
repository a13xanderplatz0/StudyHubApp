package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studyhubapp.models.Course
import com.example.studyhubapp.models.Exam

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(
    course: Course?,
    onExamClick: (Exam) -> Unit,
    onResourcesClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(course?.name ?: "Curso") }
            )
        }
    ) { paddingValues ->
        if (course == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Descripción",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = course.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                item {
                    Text(
                        text = "Exámenes",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                items(course.exams) { exam ->
                    ExamCard(
                        exam = exam,
                        onClick = { onExamClick(exam) }
                    )
                }

                item {
                    Button(
                        onClick = onResourcesClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Text("Ver Recursos")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamCard(
    exam: Exam,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = exam.title,
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = exam.description,
                style = MaterialTheme.typography.bodyMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${exam.questions.size} preguntas",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Duración: ${exam.duration} min",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
} 