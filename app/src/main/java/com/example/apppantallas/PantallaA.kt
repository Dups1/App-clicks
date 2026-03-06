package com.example.apppantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource


val nombres = listOf("Barcelona","Real Madrid","Manchester City","Liverpool","Bayern Munich","Juventus","PSG","Chelsea")
@Composable
fun PantallaA(navController: NavController){

    val seleccionados  = remember{ mutableStateListOf<String>() }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Selecciona los equipos",
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                textAlign = TextAlign.Center
        )
        Text(
            text = "Equipos seleccionados ${seleccionados.size}/2",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )
        val imagenesEquipos = mapOf(
        "Barcelona" to R.drawable.barcelona,
         "Real Madrid" to R.drawable.real_madrid,
         "Manchester City" to R.drawable.manchester_city,
        "Liverpool" to R.drawable.liverpool,
        "Bayern Munich" to R.drawable.bayern,
        "Juventus" to R.drawable.juventus,
        "PSG" to R.drawable.psg,
        "Chelsea" to R.drawable.chelsea
)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(nombres) { nombre ->
                val estaseleccionados = nombre in seleccionados
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (estaseleccionados) Color(0xFFFFCDD2) else Color.White
                    ),
                    onClick = {
                        if (estaseleccionados) {
                            seleccionados.remove(nombre)
                        } else if (seleccionados.size < 2) {
                            seleccionados.add(nombre)
                        }
                        if (seleccionados.size == 2) {
                            navController.navigate(
                                "detalles/${seleccionados[0]}/${seleccionados[1]}"
                            )
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val imagenId = imagenesEquipos[nombre]
                        if (imagenId != null) {
                            Image(
                                painter = painterResource(id = imagenId),
                                contentDescription = nombre,
                                modifier = Modifier
                                    .size(80.dp)
                            )
                        }
                        Text(
                            text = nombre,
                            fontSize = 20.sp,
                            color = if (estaseleccionados) Color.Red else Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

