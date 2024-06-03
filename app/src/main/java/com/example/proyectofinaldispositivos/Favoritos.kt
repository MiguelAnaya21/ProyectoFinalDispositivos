package com.example.proyectofinaldispositivos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import com.example.proyectofinaldispositivos.model.CartasAbdominales
import com.example.proyectofinaldispositivos.model.CartasBrazos
import com.example.proyectofinaldispositivos.model.CartasEspalda

// Vista para la lista de favoritos
class FavoritosViewModel : ViewModel() {
    val favoritos = mutableStateListOf<Any>()

    // Agregar una carta a favoritos
    fun agregarAFavoritos(carta: Any) {
        if (!favoritos.contains(carta)) {
            favoritos.add(carta)
        }
    }

    // Eliminar una carta de favoritos
    fun eliminarDeFavoritos(carta: Any) {
        favoritos.remove(carta)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favoritos(navController: NavHostController, favoritosViewModel: FavoritosViewModel) {
    Scaffold(
        topBar = {
            // Barra superior
            TopAppBar(
                title = {
                    // Título de la barra superior
                    Text(
                        text = "Favoritos",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                // Icono de retroceso
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        //Barra inferior
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                //Icono de Favoritos
                IconButton(onClick = { navController.navigate("favoritos") }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favoritos", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Icono de Menu
                IconButton(onClick = { navController.navigate("menu") }) {
                    Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Icono de Calendario
                IconButton(onClick = { navController.navigate("Calendario") }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Calendar", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Icono de Informacion
                IconButton(onClick = { navController.navigate("Informacion") }) {
                    Icon(Icons.Default.Info, contentDescription = "Informacion", modifier = Modifier.size(36.dp))
                }
            }
        }
    ) { innerPadding ->
        // Contenido de la lista de favoritos
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(favoritosViewModel.favoritos) { item ->
                when (item) {
                    is CartasEspalda -> EspaldaCard(
                        navController = navController,
                        cartasEspalda = item,
                        onFavoriteClick = { favoritosViewModel.eliminarDeFavoritos(it) },
                        onDayClick = { _, _ -> /* No se necesita acción */ },
                        modifier = Modifier.padding(8.dp)
                    )
                    is CartasAbdominales -> AbdominalesCard(
                        navController = navController,
                        cartasAbdominales = item,
                        onFavoriteClick = { favoritosViewModel.eliminarDeFavoritos(it) },
                        onDayClick = { _, _ -> /* No se necesita acción */ },
                        modifier = Modifier.padding(8.dp)
                    )
                    is CartasBrazos -> BrazoCard(
                        navController = navController,
                        cartasBrazos = item,
                        onFavoriteClick = { favoritosViewModel.eliminarDeFavoritos(it) },
                        onDayClick = { _, _ -> /* No se necesita acción */ },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}


//Vista para la lista de favoritos
@Preview(showBackground = true)
@Composable
fun FavoritosPreview() {
    Favoritos(navController = NavHostController(LocalContext.current), favoritosViewModel = FavoritosViewModel())
}
