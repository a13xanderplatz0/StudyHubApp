package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studyhubapp.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    user: User,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar o imagen de perfil
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp),
                shape = MaterialTheme.shapes.circular,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.name.first().toString(),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }

            // Información del usuario
            Text(
                text = user.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Semestre actual: ${user.currentSemester}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Estadísticas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Estadísticas",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatItem("Cursos", user.enrolledCourses.size.toString())
                        StatItem("Exámenes", user.examResults.size.toString())
                    }
                }
            }

            // Botón de cerrar sesión
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
    }
} 