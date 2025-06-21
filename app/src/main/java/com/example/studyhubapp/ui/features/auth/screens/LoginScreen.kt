package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyhubapp.ui.theme.DarkBlue
import com.example.studyhubapp.ui.theme.White

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onGoogleClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

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
            modifier = Modifier.padding(bottom = 24.dp)
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
        // Botón Google
        Button(
            onClick = onGoogleClick,
            colors = ButtonDefaults.outlinedButtonColors(containerColor = White),
            border = ButtonDefaults.outlinedButtonBorder,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(48.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Google", color = Color.Black, fontWeight = FontWeight.Medium)
        }
        // Email
        Text(
            text = "EMAIL",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp, bottom = 2.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )
        // Password
        Text(
            text = "PASSWORD",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp, bottom = 2.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar" else "Mostrar"
                    )
                }
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )
        // Botón Login
        Button(
            onClick = onLogin,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90B8F8)),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(48.dp)
                .padding(top = 24.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Login",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        // Texto para ir a Sign Up
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dont have an account ",
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                text = "Sing Up",
                color = Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
    }
} 