package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import portaria.project.domain.Encomenda
import portaria.project.ui.EncomendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncomendaScreen(navController: NavController, viewModel: EncomendaViewModel) {
    var desc by remember { mutableStateOf("") }
    var dest by remember { mutableStateOf("") }
    val encomendas by viewModel.encomendas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Entrada de Encomendas") },
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
                    OutlinedTextField(value = desc, onValueChange = { desc = it }, label = { Text("Descrição (ex: Caixa Amazon)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = dest, onValueChange = { dest = it }, label = { Text("Destinatário") }, modifier = Modifier.fillMaxWidth())
                    Button(onClick = {
                        viewModel.inserir(Encomenda(descricao = desc, destinatario = dest, status = "Pendente"))
                        desc = ""; dest = ""
                    }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Icon(Icons.Default.LocalShipping, null); Text(" Registar Entrega")
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn {
                items(encomendas) { e ->
                    ListItem(
                        headlineContent = { Text(e.destinatario) },
                        supportingContent = { Text("${e.descricao} | Status: ${e.status}") },
                        trailingContent = {
                            IconButton(onClick = { e.id?.let { viewModel.apagar(it.toInt()) } }) {
                                Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    )
                }
            }
        }
    }
}