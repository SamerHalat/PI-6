package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class VisitanteVisual(val nome: String, val rg: String, val destino: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitanteScreen(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var rg by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }

    val visitantes = remember { mutableStateListOf<VisitanteVisual>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Controlo de Visitantes") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Registar Nova Entrada", style = MaterialTheme.typography.titleMedium)
                    OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome do Visitante") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = rg, onValueChange = { rg = it }, label = { Text("RG/Documento") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = destino, onValueChange = { destino = it }, label = { Text("Destino (Apto/Bloco)") }, modifier = Modifier.fillMaxWidth())

                    Button(onClick = {
                        if(nome.isNotBlank()) {
                            visitantes.add(VisitanteVisual(nome, rg, destino))
                            nome = ""; rg = ""; destino = ""
                        }
                    }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                        Icon(Icons.Default.Badge, null)
                        Spacer(Modifier.width(8.dp))
                        Text("Registar (Front-end)")
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            LazyColumn {
                items(visitantes) { v ->
                    ListItem(
                        headlineContent = { Text(v.nome) },
                        supportingContent = { Text("Documento: ${v.rg} | Destino: ${v.destino}") }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}