package com.example.apppantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun PantallaB(navController: NavController, itemId1: String?, itemId2: String?) {
    val equipo1 = itemId1 ?: "Equipo 1"
    val equipo2 = itemId2 ?: "Equipo 2"

    var goles1 by remember { mutableStateOf(0) }
    var goles2 by remember { mutableStateOf(0) }

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

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .align(Alignment.Center)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Marcador", fontSize = 24.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        imagenesEquipos[equipo1]?.let { id ->
                            Image(
                                painter = painterResource(id = id),
                                contentDescription = equipo1,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Text(text = equipo1, fontSize = 18.sp)
                        Text(text = goles1.toString(), fontSize = 40.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = { if (goles1 > 0) goles1-- }) {
                                Text(text = "-")
                            }
                            Button(onClick = { goles1++ }) {
                                Text(text = "+")
                            }
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        imagenesEquipos[equipo2]?.let { id ->
                            Image(
                                painter = painterResource(id = id),
                                contentDescription = equipo2,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Text(text = equipo2, fontSize = 18.sp)
                        Text(text = goles2.toString(), fontSize = 40.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = { if (goles2 > 0) goles2-- }) {
                                Text(text = "-")
                            }
                            Button(onClick = { goles2++ }) {
                                Text(text = "+")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        goles1 = 0
                        goles2 = 0
                    }
                ) {
                    Text(
                        text = "Reiniciar marcador",
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        text = "Regresar a selección",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}