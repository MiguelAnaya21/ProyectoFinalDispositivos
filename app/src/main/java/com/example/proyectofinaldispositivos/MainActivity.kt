package com.example.proyectofinaldispositivos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinaldispositivos.model.CartasAbdominales
import com.example.proyectofinaldispositivos.ui.theme.ProyectoFinalDispositivosTheme
import java.util.Locale


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Se inicializa el NavCotroller para navegar entre pantallas
            val navController = rememberNavController()
            //Se inicializa el ViewModel para el Favoritos y Calendario
            val favoritosViewModel: FavoritosViewModel = viewModel()
            val calendarioViewModel: CalendarioViewModel = viewModel()

            //Se pone el tema de la aplicación
            ProyectoFinalDispositivosTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Se define en que pantalla se va a compenzar a navegar
                    NavHost(navController = navController, startDestination = "bienvenida") {
                        //Se define las cada una de las pantallas las cuales va a navegar
                        composable("bienvenida") {
                            Bienvenida(navController = navController)
                        }
                        composable("menu") {
                            Menu(navController = navController)
                        }
                        composable("abdominales") {
                            AbdominalesLista(navController = navController, favoritosViewModel, calendarioViewModel)
                        }
                        composable("Brazos") {
                            Brazos(navController = navController, favoritosViewModel, calendarioViewModel)
                        }
                        composable("Espalda") {
                            Espalda(navController = navController, favoritosViewModel, calendarioViewModel)
                        }
                        composable("favoritos") {
                            Favoritos(navController = navController, favoritosViewModel)
                        }
                        composable("informacion") {
                            Informacion(navController = navController)
                        }
                        composable("calendario") {
                            CalendarioScreen(navController = navController, calendarioViewModel = calendarioViewModel)
                        }
                    }
                }
            }
        }
    }
}

//Composable para la pantalla de Bienvenida
@Composable
fun Bienvenida(navController: NavController, modifier: Modifier = Modifier) {
    //Este es el fonden de la pantalla
    Image(
        painter = painterResource(id = R.drawable.fondonew),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
        alpha = 0.7f
    )
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Se definen los textos de la pantalla de Bienvenida
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Energia Total",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                shadow = Shadow(color = Color.Black, offset = Offset(2f, 2f), blurRadius = 4f)
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
                shadow = Shadow(color = Color.Black, offset = Offset(1f, 1f), blurRadius = 2f)
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Spacer(modifier = Modifier.height(200.dp))
        //Boton de Comenzar
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
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text(text = "Comenzar", fontSize = 20.sp)
        }
    }
}

//Vista previa de la pantalla de Bienvenida
@Preview(showBackground = true)
@Composable
fun BienvenidaPreview() {
    ProyectoFinalDispositivosTheme {
        Bienvenida(navController = rememberNavController())
    }
}