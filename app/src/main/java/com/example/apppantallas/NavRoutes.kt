package com.example.apppantallas

    sealed class Screen(val route: String) {
        object Lista : Screen("lista")
        object Detalles : Screen("detalles/{equipo1}/{equipo2}") {

            fun createRoute(equipo1: String, equipo2: String): String {
                return "detalles/$equipo1/$equipo2"
            }
        }
    }
