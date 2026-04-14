package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class EncomendaVisual(val descricao: String, val destinatario: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncomendaScreen(navController: NavController) {
    var desc by remember { mutableStateOf("") }
    var dest by remember { mutableStateOf("") }

    val encomendas = remember { mutableStateListOf<EncomendaVisual>() }

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
                    Text("Registar Entrega", style = MaterialTheme.typography.titleMedium)
                    OutlinedTextField(value = desc, onValueChange = { desc = it }, label = { Text("Descrição (ex: Caixa Amazon)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = dest, onValueChange = { dest = it }, label = { Text("Destinatário") }, modifier = Modifier.fillMaxWidth())

                    Button(onClick = {
                        if(desc.isNotBlank()) {
                            encomendas.add(EncomendaVisual(desc, dest))
                            desc = ""; dest = ""
                        }
                    }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                        Icon(Icons.Default.LocalShipping, null)
                        Spacer(Modifier.width(8.dp))
                        Text("Registar Entrega (Front-end)")
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            LazyColumn {
                items(encomendas) { e ->
                    ListItem(
                        headlineContent = { Text(e.destinatario) },
                        supportingContent = { Text("${e.descricao} | Status: Pendente") }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}