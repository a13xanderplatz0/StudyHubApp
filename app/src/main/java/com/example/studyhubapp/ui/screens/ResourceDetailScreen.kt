package com.example.studyhubapp.ui.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyhubapp.ui.theme.DarkBlue
import com.example.studyhubapp.ui.theme.White

// Modelo de archivo asociado al recurso
data class ResourceFile(
    val name: String,
    val url: String
)

@Composable
fun ResourceDetailScreen(
    resourceTitle: String,
    resourceDescription: String,
    resourceType: String,
    resourceFiles: List<ResourceFile>,
    onDownload: (ResourceFile) -> Unit,
    onOpenInNewWindow: (ResourceFile) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título principal
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(DarkBlue, RoundedCornerShape(4.dp))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "RECURSOS",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Detalle del recurso
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(White, RoundedCornerShape(4.dp))
                .border(3.dp, DarkBlue, RoundedCornerShape(4.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = resourceTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = DarkBlue
                )
                Text(
                    text = resourceDescription,
                    fontSize = 15.sp,
                    color = Color.Black
                )
                Text(
                    text = "Tipo: $resourceType",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Archivos asociados
        resourceFiles.forEach { file ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(White, RoundedCornerShape(4.dp))
                    .border(3.dp, DarkBlue, RoundedCornerShape(4.dp))
                    .padding(12.dp)
                    .padding(bottom = 16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = file.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = DarkBlue,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { onDownload(file) },
                            colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        ) {
                            Text("Descargar", color = White)
                        }
                        Button(
                            onClick = { onOpenInNewWindow(file) },
                            colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        ) {
                            Text("Abrir en nueva ventana", color = White, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
} 