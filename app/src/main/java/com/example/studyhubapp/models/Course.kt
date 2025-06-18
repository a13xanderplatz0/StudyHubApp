package com.example.studyhubapp.models

data class Course(
    val id: String,
    val name: String,
    val semester: Int,
    val description: String,
    val resources: List<Resource> = emptyList(),
    val exams: List<Exam> = emptyList()
)

data class Resource(
    val id: String,
    val title: String,
    val description: String,
    val url: String,
    val type: ResourceType
)

enum class ResourceType {
    PDF,
    VIDEO,
    IMAGE,
    LINK
}

data class Exam(
    val id: String,
    val title: String,
    val description: String,
    val questions: List<Question>,
    val duration: Int, // en minutos
    val passingScore: Int
)

data class Question(
    val id: String,
    val text: String,
    val options: List<String>,
    val correctAnswer: Int
) 