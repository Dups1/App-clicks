package com.example.apppantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apppantallas.ui.theme.AppPantallasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPantallasTheme {


                val navController = rememberNavController()
                NavHost(navController, startDestination = Screen.Lista.route) {
                    composable(Screen.Lista.route) { PantallaA(navController) }
                    composable(Screen.Detalles.route) { backStack ->
                        val equipo1 = backStack.arguments?.getString("equipo1")
                        val equipo2 = backStack.arguments?.getString("equipo2")
                        PantallaB(navController, equipo1, equipo2)
                    }

                }
            }
        }

    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppPantallasTheme {
        Greeting("Android")
    }
}