package portaria.project.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class VeiculoData(val id: String, val placa: String, val modelo: String, val cor: String, val mensalista: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeiculoListScreen(navController: NavController) {
    var busca by remember { mutableStateOf("") }

    val lista = listOf(
        VeiculoData("1", "ABC-1234", "HONDA CIVIC", "PRATA", true),
        VeiculoData("2", "XYZ-9876", "FORD KA", "PRETO", false)
    )

    val filtrada = lista.filter {
        it.placa.contains(busca, ignoreCase = true) ||
                it.modelo.contains(busca, ignoreCase = true)
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("VEÍCULOS", fontSize = 14.sp, fontWeight = FontWeight.Bold) }, navigationIcon = { IconButton(onClick = { navController.navigate("home") }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }) },
        floatingActionButton = { FloatingActionButton(onClick = { navController.navigate("veiculos_form") }, shape = RectangleShape) { Icon(Icons.Default.Add, null) } }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {

            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                placeholder = { Text("Pesquisar placa ou modelo...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape
            )
            Spacer(Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filtrada) { item ->
                    Box(modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outline).padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.placa, fontWeight = FontWeight.Black, fontSize = 16.sp)
                                Text("ID: ${item.id} | MODELO: ${item.modelo} | COR: ${item.cor}", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                                Text("MENSALISTA: ${if(item.mensalista) "SIM" else "NÃO"}", fontSize = 12.sp, color = if(item.mensalista) Color.Green else Color.Gray)
                            }
                            IconButton(onClick = { navController.navigate("veiculos_form") }) { Icon(Icons.Default.EditNote, null) }
                            IconButton(onClick = { }) { Icon(Icons.Default.DeleteOutline, null, tint = Color.Red) }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeiculoFormScreen(navController: NavController) {
    var placa by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var cor by remember { mutableStateOf("") }
    var mensalista by remember { mutableStateOf(true) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("REGISTRO DE VEÍCULO", fontSize = 14.sp, fontWeight = FontWeight.Bold) }, navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {
            OutlinedTextField(value = placa, onValueChange = { placa = it }, label = { Text("PLACA") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Row {
                OutlinedTextField(value = modelo, onValueChange = { modelo = it }, label = { Text("MODELO") }, modifier = Modifier.weight(1f))
                Spacer(Modifier.width(12.dp))
                OutlinedTextField(value = cor, onValueChange = { cor = it }, label = { Text("COR") }, modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text("VEÍCULO MENSALISTA", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Switch(checked = mensalista, onCheckedChange = { mensalista = it })
            }
            Spacer(Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("GRAVAR") }
                Spacer(Modifier.width(12.dp))
                OutlinedButton(onClick = { placa = ""; modelo = ""; cor = ""; mensalista = true }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("LIMPAR") }
            }
        }
    }
}