package com.example.proyectofinaldispositivos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinaldispositivos.model.CartasAbdominales
import com.example.proyectofinaldispositivos.ui.theme.ProyectoFinalDispositivosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val favoritosViewModel: FavoritosViewModel = viewModel()
            ProyectoFinalDispositivosTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "bienvenida") {
                        composable("bienvenida") {
                            Bienvenida(navController = navController)
                        }
                        composable("menu") {
                            Menu(navController = navController)
                        }
                        composable("abdominales") {
                            // Aquí pasamos la lista completa a AbdominalesLista
                            AbdominalesLista(navController = navController)
                        }
                        composable("Brazos") {
                            Brazos(navController = navController)
                        }
                        composable("Espalda") {
                            Espalda(navController = navController, favoritosViewModel)
                        }
                        composable("favoritos"){
                            Favoritos(navController = navController, favoritosViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Bienvenida(navController: NavController, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.fondonew),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
        alpha = 0.7f
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Energia Total",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontStyle = FontStyle.Italic // Añade estilo cursiva
            ),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Rutina de Ejercicios",
            style = TextStyle(
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(200.dp))
        Button(
            onClick = {
                navController.navigate("menu")
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Comenzar", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BienvenidaPreview() {
    ProyectoFinalDispositivosTheme {
        Bienvenida(navController = rememberNavController())
    }
}
