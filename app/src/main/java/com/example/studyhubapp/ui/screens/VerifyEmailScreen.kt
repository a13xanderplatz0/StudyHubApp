package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun VerifyEmailScreen(
    onVerifyClick: () -> Unit = {},
    contactEmail: String = "contact@mail.com"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Ícono birrete académico
        Image(
            painter = painterResource(android.R.drawable.ic_menu_upload), // Cambia por tu birrete si tienes uno
            contentDescription = "Birrete académico",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 32.dp)
        )
        // Botón VERIFICAR EMAIL
        Button(
            onClick = onVerifyClick,
            colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp)
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "VERIFY EMAIL",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        // Texto de contacto
        Text(
            text = "Do you have any\nQuestion?",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = contactEmail,
            color = Color.Red,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
} 