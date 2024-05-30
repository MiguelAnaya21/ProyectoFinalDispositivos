package com.example.proyectofinaldispositivos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.proyectofinaldispositivos.ui.theme.ProyectoFinalDispositivosTheme

@Composable
fun Menu(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                IconButton(onClick = { navController.navigate("favoritos") }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Acción al hacer clic en el icono de casa */ }) {
                    Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Acción al hacer clic en el icono de calendario */ }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Calendar", modifier = Modifier.size(36.dp))
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoproyect),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp) // Tamaño más pequeño del logo
                        .padding(top = 16.dp) // Espacio superior
                )
                Text(
                    text = "Menu",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre el logo y los botones
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ButtonRow(imageId = R.drawable.abdominales, buttonText = "Abdominales") {
                        navController.navigate("abdominales")
                    }
                    ButtonRow(imageId = R.drawable.brazos, buttonText = "Brazos") {
                        navController.navigate("brazos")
                    }
                    ButtonRow(imageId = R.drawable.espalda, buttonText = "Espalda") {
                        navController.navigate("espalda")
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonRow(
    @DrawableRes imageId: Int,
    buttonText: String,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = buttonText)
            Button(onClick = onClick) {
                Text(text = "Iniciar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    ProyectoFinalDispositivosTheme {
        // Usamos un NavHostController simulado para la vista previa
        Menu(navController = NavHostController(LocalContext.current))
    }
}
