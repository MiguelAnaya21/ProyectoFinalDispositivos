package com.example.proyectofinaldispositivos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.proyectofinaldispositivos.data.Datasource
import com.example.proyectofinaldispositivos.model.CartasEspalda


@Composable
fun EspaldaCard(
    navController: NavHostController,
    cartasEspalda: CartasEspalda,
    onFavoriteClick: (CartasEspalda) -> Unit,
    onDayClick: (String, CartasEspalda) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }
    //Muestra la información de la card
    Card(modifier = modifier.padding(8.dp).fillMaxWidth()) {
        Column {
            Box {
                //Imagen del ejercicio de espalda
                Image(
                    painter = painterResource(cartasEspalda.imageResourceId),
                    contentDescription = stringResource(cartasEspalda.stringResourceId2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp),
                    contentScale = ContentScale.Crop
                )
                //Icono de favoritos
                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        onFavoriteClick(cartasEspalda)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
            //Nombre del ejercicio de espalda
            Text(
                text = stringResource(id = cartasEspalda.stringResourceId2),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            //Descripción del ejercicio de espalda
            Text(
                text = stringResource(id = cartasEspalda.stringResourceId),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            //Botones para seleccionar el día de la semana
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly // Distribuye uniformemente los botones
            ) {
                //Lista de los dias de la semana
                val daysOfWeek = listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom")
                //Crea un boton para cada dia de la semana
                for (day in daysOfWeek) {
                    Button(
                        onClick = { onDayClick(day, cartasEspalda) },
                        modifier = Modifier
                            .padding(0.dp) // Elimina el padding
                            .height(40.dp) // Ajusta la altura de los botones
                            .width(40.dp), // Ajusta el ancho de los botones
                        shape = CircleShape, // Forma circular para los botones
                        contentPadding = PaddingValues(0.dp) // Elimina el padding del contenido
                    ) {
                        Text(
                            text = day.first().toString(),
                            fontSize = 14.sp, // Ajusta el tamaño del texto
                            modifier = Modifier.padding(0.dp) // Elimina el padding
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Espalda(navController: NavHostController, favoritosViewModel: FavoritosViewModel, calendarioViewModel: CalendarioViewModel) {
    Scaffold(
        topBar = {
            //Muestra lo de la barra superior
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        //Logo de la app
                        Image(
                            painter = painterResource(id = R.drawable.logoproyect),
                            contentDescription = null, // Puedes poner una descripción si es necesario
                            modifier = Modifier.size(36.dp).padding(end = 8.dp)
                        )
                        //Nombre de la pantalla
                        Text(
                            text = "Espalda",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                },
                //Boton para regresar
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
        //Muestra lo de la barra inferior
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                //Icono de favoritos
                IconButton(onClick = { navController.navigate("favoritos") }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favoritos", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Icono de menu
                IconButton(onClick = { navController.navigate("menu") }) {
                    Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Icono de calendario
                IconButton(onClick = { navController.navigate("calendario") }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Calendar", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Icono de informacion
                IconButton(onClick = { navController.navigate("Informacion") }) {
                    Icon(Icons.Default.Info, contentDescription = "Informacion", modifier = Modifier.size(36.dp))
                }
            }
        }
    ) { innerPadding ->
        //Columna desplazable que contiene la lista de ejercicios de espalda
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(Datasource().loadEspalda()) { espalda ->
                //Cada elemento de la lista de los ejercicios de espalda
                EspaldaCard(
                    navController = navController,
                    cartasEspalda = espalda,
                    onFavoriteClick = { favoritosViewModel.agregarAFavoritos(it) },
                    onDayClick = { day, card -> calendarioViewModel.addEspaldaCardToDay(day, card)},
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

//Vista previa de la pantalla de espalda
@Preview(showBackground = true)
@Composable
fun EspaldaPreview() {
    Espalda(
        navController = NavHostController(LocalContext.current),
        favoritosViewModel = FavoritosViewModel(),
        calendarioViewModel = CalendarioViewModel()
    )
}
