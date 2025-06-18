package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyhubapp.ui.theme.DarkBlue
import com.example.studyhubapp.ui.theme.White

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(android.R.drawable.ic_menu_info_details), // Cambia por tu logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(90.dp)
                .padding(bottom = 8.dp)
        )
        // Texto estilizado
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = "STUDY",
                color = Color(0xFF6D7B8A),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                letterSpacing = 2.sp
            )
            Text(
                text = "HUB",
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                letterSpacing = 2.sp
            )
        }
        // Bienvenida
        Text(
            text = "Welcome to StudyHub",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Ícono birrete académico
        Image(
            painter = painterResource(android.R.drawable.ic_menu_upload), // Cambia por tu birrete si tienes uno
            contentDescription = "Birrete académico",
            modifier = Modifier
                .size(64.dp)
                .padding(bottom = 8.dp)
        )
        // Texto descriptivo
        Text(
            text = "Una plataforma académica dedicada al almacenamiento y consulta de exámenes de todos los semestres de la carrera de Ciencias de la Computación en la Universidad Católica San Pablo.",
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 12.dp)
        )
        Text(
            text = "Permite a los usuarios subir archivos y acceder a simulacros de evaluación para una preparación integral.",
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.85f)
        )
    }
} 