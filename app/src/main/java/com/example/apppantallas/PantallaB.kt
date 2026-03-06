package com.example.apppantallas

import android.media.MediaPlayer
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    var isSimulando by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val mediaPlayerFondo = remember {
        MediaPlayer.create(context, R.raw.fondo).apply {
            isLooping = true
            start()
        }
    }
    val mediaPlayerIncremento = remember {
        MediaPlayer.create(context, R.raw.gool)
    }
    val mediaPlayerDecremento = remember {
        MediaPlayer.create(context, R.raw.anulado)
    }
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayerFondo.release()
            mediaPlayerIncremento.release()
            mediaPlayerDecremento.release()
        }
    }

    LaunchedEffect(isSimulando) {
        if (isSimulando) {
            while (true) {
                delay(4000)
                val equipo = (0..1).random()
                val accion = (0..3).random()
                if (equipo == 0) {
                    if (accion != 0) {
                        goles1++
                        mediaPlayerIncremento.seekTo(0)
                        mediaPlayerIncremento.start()
                    } else if (goles1 > 0) {
                        goles1--
                        mediaPlayerDecremento.seekTo(0)
                        mediaPlayerDecremento.start()
                    }
                } else {
                    if (accion != 0) {
                        goles2++
                        mediaPlayerIncremento.seekTo(0)
                        mediaPlayerIncremento.start()
                    } else if (goles2 > 0) {
                        goles2--
                        mediaPlayerDecremento.seekTo(0)
                        mediaPlayerDecremento.start()
                    }
                }
            }
        }
    }

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
                .fillMaxWidth(0.8f)
                .align(Alignment.Center)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Marcador", fontSize = 24.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Equipo 1 - izquierda
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        imagenesEquipos[equipo1]?.let { id ->
                            Image(
                                painter = painterResource(id = id),
                                contentDescription = equipo1,
                                modifier = Modifier.size(140.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Text(text = equipo1, fontSize = 18.sp, textAlign = TextAlign.Center)
                    }

                    // Marcador central
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = goles1.toString(), fontSize = 48.sp)
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(
                                        onClick = {
                                            if (goles1 > 0) {
                                                goles1--
                                                mediaPlayerDecremento.seekTo(0)
                                                mediaPlayerDecremento.start()
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600))
                                    ) {
                                        Text(text = "-", fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                                    }
                                    Button(
                                        onClick = {
                                            goles1++
                                            mediaPlayerIncremento.seekTo(0)
                                            mediaPlayerIncremento.start()
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600))
                                    ) {
                                        Text(text = "+", fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                                    }
                                }
                            }

                            Text(text = " - ", fontSize = 40.sp)

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = goles2.toString(), fontSize = 48.sp)
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(
                                        onClick = {
                                            if (goles2 > 0) {
                                                goles2--
                                                mediaPlayerDecremento.seekTo(0)
                                                mediaPlayerDecremento.start()
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600))
                                    ) {
                                        Text(text = "-", fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                                    }
                                    Button(
                                        onClick = {
                                            goles2++
                                            mediaPlayerIncremento.seekTo(0)
                                            mediaPlayerIncremento.start()
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600))
                                    ) {
                                        Text(text = "+", fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                                    }
                                }
                            }
                        }
                    }

                    // Equipo 2 - derecha
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        imagenesEquipos[equipo2]?.let { id ->
                            Image(
                                painter = painterResource(id = id),
                                contentDescription = equipo2,
                                modifier = Modifier.size(140.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Text(text = equipo2, fontSize = 18.sp, textAlign = TextAlign.Center)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { isSimulando = !isSimulando },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSimulando) Color(0xFF388E3C) else Color(0xFF1565C0)
                        )
                    ) {
                        Text(
                            text = if (isSimulando) "Detener simulación" else "Simular partido",
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {
                            goles1 = 0
                            goles2 = 0
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600))
                    ) {
                        Text(
                            text = "Reiniciar marcador",
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600))
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
}