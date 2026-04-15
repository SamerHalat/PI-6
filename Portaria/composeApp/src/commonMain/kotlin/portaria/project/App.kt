package portaria.project

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import portaria.project.ui.screens.*

@Composable
fun App() {
    val darkColorScheme = darkColorScheme(
        primary = Color.White,
        onPrimary = Color.Black,
        surface = Color(0xFF121212),
        background = Color(0xFF0A0A0A),
        outline = Color(0xFF333333)
    )

    MaterialTheme(colorScheme = darkColorScheme, shapes = Shapes(medium = RoundedCornerShape(0.dp))) {
        val navController = rememberNavController()
        Surface(color = MaterialTheme.colorScheme.background) {
            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LoginScreen(navController) }
                composable("registro") { RegistroScreen(navController) }
                composable("home") { HomeScreen(navController) }
                composable("apartamentos_lista") { ApartamentoListScreen(navController) }
                composable("apartamentos_form") { ApartamentoFormScreen(navController) }
                composable("moradores_lista") { MoradorListScreen(navController) }
                composable("moradores_form") { MoradorFormScreen(navController) }
                composable("visitantes_lista") { VisitanteListScreen(navController) }
                composable("visitantes_form") { VisitanteFormScreen(navController) }
                composable("veiculos_lista") { VeiculoListScreen(navController) }
                composable("veiculos_form") { VeiculoFormScreen(navController) }
                composable("encomendas_lista") { EncomendaListScreen(navController) }
                composable("encomendas_form") { EncomendaFormScreen(navController) }
            }
        }
    }
}