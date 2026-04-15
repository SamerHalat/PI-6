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
                actions = { IconButton(onClick = { navController.navigate("login") }) { Icon(Icons.Default.Logout, null) } }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 800.dp) // Limita a largura máxima para não esticar ao infinito
                    .padding(24.dp)
            ) {
                Text("TERMINAL DE OPERAÇÕES", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                Spacer(modifier = Modifier.height(24.dp))

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item { MenuBox("APARTAMENTOS", Icons.Default.Domain) { navController.navigate("apartamentos_lista") } }
                    item { MenuBox("MORADORES", Icons.Default.Home) { navController.navigate("moradores_lista") } }
                    item { MenuBox("VISITANTES", Icons.Default.Badge) { navController.navigate("visitantes_lista") } }
                    item { MenuBox("VEÍCULOS", Icons.Default.DirectionsCar) { navController.navigate("veiculos_lista") } }
                    item { MenuBox("ENTREGAS", Icons.Default.Inventory) { navController.navigate("encomendas_lista") } }
                }
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