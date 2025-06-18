package com.example.studyhubapp.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyhubapp.ui.screens.*
import androidx.compose.ui.platform.LocalContext

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Course : Screen("course/{courseId}") {
        fun createRoute(courseId: String) = "course/$courseId"
    }
    object Exam : Screen("exam/{examId}") {
        fun createRoute(examId: String) = "exam/$examId"
    }
    object Resources : Screen("resources/{courseId}") {
        fun createRoute(courseId: String) = "resources/$courseId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            SignUpScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                courses = emptyList(), // TODO: Implementar ViewModel
                onCourseClick = { course ->
                    navController.navigate(Screen.Course.createRoute(course.id))
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                user = null, // TODO: Implementar ViewModel
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Course.route) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            // TODO: Implementar ViewModel para obtener el curso
            CourseScreen(
                course = null,
                onExamClick = { exam ->
                    navController.navigate(Screen.Exam.createRoute(exam.id))
                },
                onResourcesClick = {
                    navController.navigate(Screen.Resources.createRoute(courseId ?: ""))
                }
            )
        }

        composable(Screen.Exam.route) { backStackEntry ->
            val examId = backStackEntry.arguments?.getString("examId")
            // TODO: Implementar ViewModel para obtener el examen
            ExamScreen(
                exam = null,
                onExamComplete = { answers ->
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Resources.route) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            // Ejemplo de recursos
            val exampleResources = listOf(
                ResourceItem(
                    title = "Apuntes de Matemáticas",
                    description = "Conceptos básicos de álgebra lineal",
                    type = "APUNTE",
                    date = "11/5/2025",
                    files = listOf(
                        ResourceFile("Examen Resuelto 1", "https://ejemplo.com/examen1.pdf"),
                        ResourceFile("Examen Resuelto 2", "https://ejemplo.com/examen2.pdf")
                    )
                ),
                ResourceItem(
                    title = "Calculo Final",
                    description = "Calculo final",
                    type = "EXAMEN",
                    date = "11/5/2025",
                    files = listOf(
                        ResourceFile("Examen Resuelto", "https://ejemplo.com/examen3.pdf")
                    )
                )
            )
            var selectedResource by remember { mutableStateOf<ResourceItem?>(null) }
            if (selectedResource == null) {
                ResourcesScreen(
                    resources = exampleResources,
                    onResourceDetail = { selectedResource = it }
                )
            } else {
                ResourceDetailScreen(
                    resourceTitle = selectedResource!!.title,
                    resourceDescription = selectedResource!!.description,
                    resourceType = selectedResource!!.type,
                    resourceFiles = selectedResource!!.files,
                    onDownload = { file ->
                        // Lógica de descarga
                        // Por ejemplo, abrir el enlace en el navegador para descargar
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(file.url))
                        LocalContext.current.startActivity(intent)
                    },
                    onOpenInNewWindow = { file ->
                        // Lógica para abrir en nueva ventana (navegador)
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(file.url))
                        LocalContext.current.startActivity(intent)
                    }
                )
            }
        }
    }
} 