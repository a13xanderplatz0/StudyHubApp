package com.example.studyhubapp.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val currentSemester: Int,
    val enrolledCourses: List<String> = emptyList(), // Lista de IDs de cursos
    val examResults: List<ExamResult> = emptyList()
)

data class ExamResult(
    val examId: String,
    val score: Int,
    val completedAt: Long,
    val answers: List<Int> // Índices de las respuestas seleccionadas
) 