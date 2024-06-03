package com.example.proyectofinaldispositivos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinaldispositivos.model.CartasAbdominales
import com.example.proyectofinaldispositivos.model.CartasBrazos
import com.example.proyectofinaldispositivos.model.CartasEspalda

// Clase que junta las listas de ejercicios por dia
data class DailyCards(
    val abdominales: MutableList<CartasAbdominales> = mutableListOf(),
    val brazos: MutableList<CartasBrazos> = mutableListOf(),
    val espalda: MutableList<CartasEspalda> = mutableListOf()
)

// Es la vista de la pantalla Calendario por cada dia de la semana
class CalendarioViewModel : ViewModel() {
    private val _cardsByDay = mutableStateMapOf<String, DailyCards>(
        "Lun" to DailyCards(),
        "Mar" to DailyCards(),
        "Mie" to DailyCards(),
        "Jue" to DailyCards(),
        "Vie" to DailyCards(),
        "Sab" to DailyCards(),
        "Dom" to DailyCards()
    )

    // cartas por dia
    val cardsByDay: Map<String, DailyCards> = _cardsByDay

    //Añade las cartas por dia del ejercicio de abdominales
    fun addAbdominalesCardToDay(day: String, card: CartasAbdominales) {
        _cardsByDay[day]?.abdominales?.add(card)
    }

    //Añade las cartas por dia del ejercicio de brazos
    fun addBrazosCardToDay(day: String, card: CartasBrazos) {
        _cardsByDay[day]?.brazos?.add(card)
    }

    //Añade las cartas por dia del ejercicio de espalda
    fun addEspaldaCardToDay(day: String, card: CartasEspalda) {
        _cardsByDay[day]?.espalda?.add(card)
    }

    // Limpia las cartas por dia
    fun clearDay(day: String) {
        _cardsByDay[day]?.abdominales?.clear()
        _cardsByDay[day]?.brazos?.clear()
        _cardsByDay[day]?.espalda?.clear()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarioScreen(navController: NavHostController, calendarioViewModel: CalendarioViewModel) {
    val daysOfWeek = listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom")
    Scaffold(
        topBar = {
            //Barra de la parte superior
            TopAppBar(
                //Titulo de la barra
                title = { Text("Calendario", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(
                        onClick = { navController.navigate("calendario") }
                    ) {
                        //Actualiza las listas de ejercicios por dia
                        Icon(Icons.Default.Refresh, contentDescription = "Actualizar")
                    }
                },
                //Icono de regresar
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
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
        //Columna para las cards de los ejercicios por dia
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(daysOfWeek) { day ->
                val dailyCards = calendarioViewModel.cardsByDay[day]
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Dia de la semana
                        Text(
                            text = day,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        //Boton para limpiar las cartas por dia
                        Button(onClick = { calendarioViewModel.clearDay(day) }) {
                            Text("Limpiar")
                        }
                    }

                    dailyCards?.let { cards ->
                        if (cards.abdominales.isNotEmpty() || cards.brazos.isNotEmpty() || cards.espalda.isNotEmpty()) {
                            // Abdominales cards
                            cards.abdominales.forEach { card ->
                                AbdominalesCard(
                                    navController = navController,
                                    cartasAbdominales = card,
                                    onFavoriteClick = {},
                                    onDayClick = { _, _ ->},
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            // Brazos cards
                            cards.brazos.forEach { card ->
                                BrazoCard(
                                    navController = navController,
                                    cartasBrazos = card,
                                    onFavoriteClick = { /* No se necesita aquí */ },
                                    onDayClick = { _, _ -> /* No se necesita aquí */ },
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            // Espalda cards
                            cards.espalda.forEach { card ->
                                EspaldaCard(
                                    navController = navController,
                                    cartasEspalda = card,
                                    onFavoriteClick = { /* No se necesita aquí */ },
                                    onDayClick = { _, _ -> /* No se necesita aquí */ },
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            //Muestra un mensaje si no hay ejercicios asignados
                        } else {
                            Text("No hay ejercicios asignados.")
                        }
                    } ?: Text("No hay ejercicios asignados.")
                }
            }
        }
    }
}
