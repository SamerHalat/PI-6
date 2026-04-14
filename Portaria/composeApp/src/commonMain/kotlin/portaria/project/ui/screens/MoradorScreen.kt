package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import portaria.project.domain.Morador
import portaria.project.ui.MoradorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoradorScreen(navController: NavController, viewModel: MoradorViewModel) {
    var nome by remember { mutableStateOf("") }
    var apto by remember { mutableStateOf("") }
    var bloco by remember { mutableStateOf("") }
    val moradores by viewModel.moradores.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestão de Moradores") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Registar Novo Morador", style = MaterialTheme.typography.titleMedium)
                    OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome Completo") }, modifier = Modifier.fillMaxWidth())
                    Row {
                        OutlinedTextField(value = apto, onValueChange = { apto = it }, label = { Text("Apartamento") }, modifier = Modifier.weight(1f))
                        Spacer(Modifier.width(8.dp))
                        OutlinedTextField(value = bloco, onValueChange = { bloco = it }, label = { Text("Bloco") }, modifier = Modifier.weight(1f))
                    }
                    Button(onClick = {
                        viewModel.inserir(Morador(nome = nome, apto = apto, bloco = bloco))
                        nome = ""; apto = ""; bloco = ""
                    }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Icon(Icons.Default.PersonAdd, null); Text(" Adicionar")
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn {
                items(moradores) { item ->
                    ListItem(
                        headlineContent = { Text(item.nome) },
                        supportingContent = { Text("Apto: ${item.apto} | Bloco: ${item.bloco}") },
                        trailingContent = {
                            IconButton(onClick = { item.id?.let { viewModel.apagar(it.toInt()) } }) {
                                Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    )
                }
            }
        }
    }
}