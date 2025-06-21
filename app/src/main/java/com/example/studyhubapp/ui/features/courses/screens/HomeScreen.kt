package com.example.studyhubapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    coursesBySemester: Map<Int, List<String>>, // Mapa de semestre a lista de cursos
    onCourseClick: (String) -> Unit,
    onMenuClick: () -> Unit,
    onLogoClick: () -> Unit
) {
    var expandedSemester by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú", tint = White)
                    }
                },
                actions = {
                    IconButton(onClick = onLogoClick) {
                        // Puedes reemplazar painterResource con tu logo
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_info_details),
                            contentDescription = "Logo",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue
                )
            )
        },
        containerColor = White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(paddingValues)
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (semester in 1..10) {
                SemesterButton(
                    semester = semester,
                    expanded = expandedSemester == semester,
                    onClick = {
                        expandedSemester = if (expandedSemester == semester) null else semester
                    }
                )
                if (expandedSemester == semester) {
                    val courses = coursesBySemester[semester] ?: emptyList()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                            .padding(bottom = 8.dp)
                    ) {
                        courses.forEach { course ->
                            Text(
                                text = course,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp, vertical = 8.dp)
                                    .clickable { onCourseClick(course) },
                                color = DarkBlue,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun SemesterButton(semester: Int, expanded: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(DarkBlue, RoundedCornerShape(6.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "SEMESTRE ${toRoman(semester)}",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Expandir",
                tint = White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

fun toRoman(number: Int): String {
    val romans = listOf(
        "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
    )
    return if (number in 1..10) romans[number - 1] else number.toString()
} 