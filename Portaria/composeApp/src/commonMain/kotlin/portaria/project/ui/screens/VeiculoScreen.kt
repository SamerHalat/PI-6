package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import portaria.project.domain.Veiculo
import portaria.project.ui.VeiculoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeiculoScreen(navController: NavController, viewModel: VeiculoViewModel) {
    var placa by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var cor by remember { mutableStateOf("") }
    val veiculos by viewModel.veiculos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registo de Veículos") },
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
                    OutlinedTextField(value = placa, onValueChange = { placa = it }, label = { Text("Placa") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = modelo, onValueChange = { modelo = it }, label = { Text("Modelo/Marca") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = cor, onValueChange = { cor = it }, label = { Text("Cor") }, modifier = Modifier.fillMaxWidth())
                    Button(onClick = {
                        viewModel.inserir(Veiculo(placa = placa, modelo = modelo, cor = cor))
                        placa = ""; modelo = ""; cor = ""
                    }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Icon(Icons.Default.DirectionsCar, null); Text(" Registar Veículo")
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn {
                items(veiculos) { v ->
                    ListItem(
                        headlineContent = { Text(v.placa) },
                        supportingContent = { Text("${v.modelo} - ${v.cor}") },
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