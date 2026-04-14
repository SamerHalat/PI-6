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
    // Definindo uma paleta de cores "Premium Dark"
    val darkColorScheme = darkColorScheme(
        primary = Color.White,
        onPrimary = Color.Black,
        surface = Color(0xFF121212),
        background = Color(0xFF0A0A0A),
        outline = Color(0xFF333333)
    )

    // Removendo os cantos arredondados para um visual de "linhas retas"
    val straightShapes = Shapes(
        extraSmall = RoundedCornerShape(0.dp),
        small = RoundedCornerShape(2.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp),
        extraLarge = RoundedCornerShape(0.dp)
    )

    MaterialTheme(colorScheme = darkColorScheme, shapes = straightShapes) {
        val navController = rememberNavController()
        Surface(color = MaterialTheme.colorScheme.background) {
            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LoginScreen(navController) }
                composable("registro") { RegistroScreen(navController) }
                composable("home") { HomeScreen(navController) }
                composable("moradores") { MoradorScreen(navController) }
                composable("visitantes") { VisitanteScreen(navController) }
                composable("veiculos") { VeiculoScreen(navController) }
                composable("encomendas") { EncomendaScreen(navController) }
            }
        }
    }
}