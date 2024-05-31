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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
                            AbdominalesLista(navController = navController, favoritosViewModel)
                        }
                        composable("Brazos") {
                            Brazos(navController = navController, favoritosViewModel)
                        }
                        composable("Espalda") {
                            Espalda(navController = navController, favoritosViewModel)
                        }
                        composable("favoritos"){
                            Favoritos(navController = navController, favoritosViewModel)
                        }
                        composable("informacion"){
                            Informacion(navController = navController)
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
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Energia Total",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            ),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Rutina de Ejercicios",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                fontFamily = FontFamily.Serif,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(1f, 1f),
                    blurRadius = 2f
                )
            ),
            modifier = Modifier.padding(bottom = 32.dp)
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
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text(text = "Comenzar", fontSize = 20.sp)
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
