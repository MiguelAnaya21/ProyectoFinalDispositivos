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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.proyectofinaldispositivos.ui.theme.ProyectoFinalDispositivosTheme

@Composable
fun Menu(navController: NavHostController) {
    val isSystemDarkTheme = isSystemInDarkTheme()
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
                IconButton(onClick = { navController.navigate("calendario") }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Calendar", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { navController.navigate("informacion") }) {
                    Icon(Icons.Default.Info, contentDescription = "Informacion", modifier = Modifier.size(36.dp))
                }
            }
        }
    ) { innerPadding ->
        ProyectoFinalDispositivosTheme(
            darkTheme = isSystemDarkTheme,
            content = {
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
                            verticalArrangement = Arrangement.spacedBy(5.dp)
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
        )
    }
}

@Composable
fun ButtonRow(
    @DrawableRes imageId: Int,
    buttonText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.size(125.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Button(onClick = onClick, modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = "Iniciar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.scrim
                    )
                }
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
