package com.example.proyectofinaldispositivos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import com.example.proyectofinaldispositivos.data.Datasource
import com.example.proyectofinaldispositivos.model.CartasBrazos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrazoCard(
    navController: NavHostController,
    cartasBrazos: CartasBrazos,
    onFavoriteClick: (CartasBrazos) -> Unit,
    onDayClick: (String, CartasBrazos) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }
    //Muestra la informacion de cada uno de los ejercicios del brazo
    Card(modifier = modifier.padding(8.dp).fillMaxWidth()) {
        Column {
            Box {
                //Muestra la imagen del ejercicio para brazo
                Image(
                    painter = painterResource(cartasBrazos.imageResourceId),
                    contentDescription = stringResource(cartasBrazos.stringResourceId2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp),
                    contentScale = ContentScale.Crop
                )
                //Muestra el icono de favoritos
                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        onFavoriteClick(cartasBrazos)
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
            //Muestra el nombre del ejercicio
            Text(
                text = stringResource(id = cartasBrazos.stringResourceId2),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            //Muestra la descripcion del ejercicio
            Text(
                text = stringResource(id = cartasBrazos.stringResourceId),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            //Muestra los botones para seleccionar el dia del ejercicio
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly // Distribuye uniformemente los botones
            ) {
                //Muestra los botones para seleccionar el dia del ejercicio
                val daysOfWeek = listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom")
                for (day in daysOfWeek) {
                    Button(
                        onClick = { onDayClick(day, cartasBrazos) },
                        modifier = Modifier
                            .padding(0.dp) // Elimina el padding
                            .height(40.dp) // Ajusta la altura de los botones
                            .width(40.dp), // Ajusta el ancho de los botones
                        shape = CircleShape, // Forma circular para los botones
                        contentPadding = PaddingValues(0.dp) // Elimina el padding del contenido
                    ) {
                        //Muestra el dia del ejercicio
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
fun Brazos(navController: NavHostController, favoritosViewModel: FavoritosViewModel, calendarioViewModel: CalendarioViewModel) {
    Scaffold(
        topBar = {
            //Contenido de la barra superior
            TopAppBar(
                title = {
                    Text(
                        text = "Brazos",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                //Muestra el icono de retroceso
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
        //Contenido de la barra inferior
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                //Muestra el icono de favoritos
                IconButton(onClick = { navController.navigate("favoritos") }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favoritos", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Muestra el icono de menu
                IconButton(onClick = { navController.navigate("menu") }) {
                    Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Muestra el icono de calendario
                IconButton(onClick = { navController.navigate("calendario") }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Calendar", modifier = Modifier.size(36.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                //Muestra el icono de informacion
                IconButton(onClick = { navController.navigate("Informacion") }) {
                    Icon(Icons.Default.Info, contentDescription = "Informacion", modifier = Modifier.size(36.dp))
                }
            }
        }
    ) { innerPadding ->
        //Columna para mostrar los ejercicios del brazo
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(Datasource().loadBrazos()) { brazos ->
                //Muestra los ejercicios del brazo
                BrazoCard(
                    navController = navController,
                    cartasBrazos = brazos,
                    onFavoriteClick = { favoritosViewModel.agregarAFavoritos(it) },
                    onDayClick = { day, card -> calendarioViewModel.addBrazosCardToDay(day, card)},
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

//Vista previa de la pantalla de Brazos
@Preview(showBackground = true)
@Composable
fun BrazosPreview() {
    Brazos(navController = NavHostController(LocalContext.current), favoritosViewModel = FavoritosViewModel(), calendarioViewModel = CalendarioViewModel())
}