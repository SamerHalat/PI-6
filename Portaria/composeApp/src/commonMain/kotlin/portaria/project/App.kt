@Composable
fun App() {
    val navController = rememberNavController()

    // Instancia os ViewModels (devem ser os mesmos que já tens no projeto)
    val moradorVM = remember { MoradorViewModel() }
    val visitanteVM = remember { VisitanteViewModel() }
    val veiculoVM = remember { VeiculoViewModel() }
    val encomendaVM = remember { EncomendaViewModel() }

    MaterialTheme {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(navController) }
            composable("home") { HomeScreen(navController) }

            // Cada rota agora recebe o seu ViewModel específico
            composable("moradores") { MoradorScreen(navController, moradorVM) }
            composable("visitantes") { VisitanteScreen(navController, visitanteVM) }
            composable("veiculos") { VeiculoScreen(navController, veiculoVM) }
            composable("encomendas") { EncomendaScreen(navController, encomendaVM) }
        }
    }
}