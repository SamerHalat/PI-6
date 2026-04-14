package portaria.project.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("SECUREENTRY", fontSize = 16.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp) },
                actions = {
                    IconButton(onClick = { navController.navigate("login") }) {
                        Icon(Icons.Default.Logout, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {
            Text("TERMINAL DE OPERAÇÕES", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { MenuBox("MORADORES", Icons.Default.Home) { navController.navigate("moradores") } }
                item { MenuBox("VISITANTES", Icons.Default.Badge) { navController.navigate("visitantes") } }
                item { MenuBox("VEÍCULOS", Icons.Default.DirectionsCar) { navController.navigate("veiculos") } }
                item { MenuBox("ENTREGAS", Icons.Default.Inventory) { navController.navigate("encomendas") } }
            }
        }
    }
}

@Composable
fun MenuBox(titulo: String, icone: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Icon(icone, null, modifier = Modifier.align(Alignment.TopEnd).size(32.dp))
        Text(titulo, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}