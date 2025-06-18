package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyhubapp.models.Resource
import com.example.studyhubapp.models.ResourceType
import com.example.studyhubapp.ui.theme.DarkBlue
import com.example.studyhubapp.ui.theme.White

// Modelo de recurso para ejemplo
data class ResourceItem(
    val title: String,
    val description: String,
    val type: String,
    val date: String,
    val files: List<ResourceFile>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(
    resources: List<ResourceItem>,
    onResourceDetail: (ResourceItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Barra superior y título
        Spacer(modifier = Modifier.height(16.dp))
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
        // Lista de recursos
        resources.forEach { resource ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(White, RoundedCornerShape(4.dp))
                    .border(3.dp, DarkBlue, RoundedCornerShape(4.dp))
                    .padding(12.dp)
                    .padding(bottom = 16.dp)
                    .clickable { onResourceDetail(resource) }
            ) {
                Column {
                    Text(
                        text = resource.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = DarkBlue
                    )
                    Text(
                        text = resource.description,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Tipo: ${resource.type}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Publicado: ${resource.date}",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onResourceDetail(resource) },
                        colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Ver Recurso", color = White)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceCard(
    resource: Resource,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = resource.title,
                    style = MaterialTheme.typography.titleMedium
                )
                ResourceTypeIcon(resource.type)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = resource.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ResourceTypeIcon(type: ResourceType) {
    val icon = when (type) {
        ResourceType.PDF -> Icons.Default.PictureAsPdf
        ResourceType.VIDEO -> Icons.Default.VideoLibrary
        ResourceType.IMAGE -> Icons.Default.Image
        ResourceType.LINK -> Icons.Default.Link
    }
    
    Icon(
        imageVector = icon,
        contentDescription = type.name,
        tint = MaterialTheme.colorScheme.primary
    )
} 