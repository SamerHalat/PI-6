package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import portaria.project.domain.Visitante
import portaria.project.ui.VisitanteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitanteScreen(navController: NavController, viewModel: VisitanteViewModel) {
    var nome by remember { mutableStateOf("") }
    var rg by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    val visitantes by viewModel.visitantes.collectAsState()

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
                    OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome do Visitante") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = rg, onValueChange = { rg = it }, label = { Text("RG/Documento") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = destino, onValueChange = { destino = it }, label = { Text("Destino (Apto/Bloco)") }, modifier = Modifier.fillMaxWidth())
                    Button(onClick = {
                        viewModel.inserir(Visitante(nome = nome, rg = rg, destino = destino))
                        nome = ""; rg = ""; destino = ""
                    }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Icon(Icons.Default.Badge, null); Text(" Registar Entrada")
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn {
                items(visitantes) { v ->
                    ListItem(
                        headlineContent = { Text(v.nome) },
                        supportingContent = { Text("Documento: ${v.rg} | Destino: ${v.destino}") },
                        trailingContent = {
                            IconButton(onClick = { v.id?.let { viewModel.apagar(it.toInt()) } }) {
                                Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    )
                }
            }
        }
    }
}