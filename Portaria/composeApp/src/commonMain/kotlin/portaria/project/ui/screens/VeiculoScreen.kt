package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class VeiculoVisual(val placa: String, val modelo: String, val cor: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeiculoScreen(navController: NavController) {
    var placa by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var cor by remember { mutableStateOf("") }

    val veiculos = remember { mutableStateListOf<VeiculoVisual>() }

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
                    Text("Adicionar Veículo", style = MaterialTheme.typography.titleMedium)
                    OutlinedTextField(value = placa, onValueChange = { placa = it }, label = { Text("Placa") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = modelo, onValueChange = { modelo = it }, label = { Text("Modelo/Marca") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = cor, onValueChange = { cor = it }, label = { Text("Cor") }, modifier = Modifier.fillMaxWidth())

                    Button(onClick = {
                        if(placa.isNotBlank()) {
                            veiculos.add(VeiculoVisual(placa, modelo, cor))
                            placa = ""; modelo = ""; cor = ""
                        }
                    }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                        Icon(Icons.Default.DirectionsCar, null)
                        Spacer(Modifier.width(8.dp))
                        Text("Registar Veículo (Front-end)")
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            LazyColumn {
                items(veiculos) { v ->
                    ListItem(
                        headlineContent = { Text(v.placa) },
                        supportingContent = { Text("${v.modelo} - ${v.cor}") }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}