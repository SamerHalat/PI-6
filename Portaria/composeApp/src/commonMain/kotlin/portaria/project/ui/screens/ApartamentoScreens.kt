package portaria.project.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

data class AptoData(val id: String, val numero: String, val bloco: String, val moradores: List<String>, val veiculos: List<String>)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApartamentoListScreen(navController: NavController) {
    var busca by remember { mutableStateOf("") }
    val listaOriginal = listOf(
        AptoData("1", "101", "TORRE A", listOf("Samer Nassir Halat"), listOf("Honda Civic - ABC1234")),
        AptoData("2", "202", "TORRE B", listOf("Maria Oliveira"), listOf("Ford Ka - XYZ5678"))
    )
    val listaFiltrada = listaOriginal.filter { it.numero.contains(busca) || it.moradores.any { m -> m.contains(busca, ignoreCase = true) } }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("UNIDADES", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("apartamentos_form") }, shape = RectangleShape) { Icon(Icons.Default.Add, null) }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(24.dp)) {

            OutlinedTextField(
                value = busca, onValueChange = { busca = it },
                placeholder = { Text("Pesquisar por número ou morador...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                modifier = Modifier.fillMaxWidth(), shape = RectangleShape
            )
            Spacer(Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(listaFiltrada) { item ->
                    Box(modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outline).padding(16.dp)) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("APTO ${item.numero} | ${item.bloco}", fontWeight = FontWeight.Black, fontSize = 18.sp)
                                }
                                IconButton(onClick = { }) { Icon(Icons.Default.EditNote, null) }
                                IconButton(onClick = { }) { Icon(Icons.Default.DeleteOutline, null, tint = Color.Red) }
                            }
                            Text("MORADORES: ${item.moradores.joinToString(", ")}", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                            Text("VEÍCULOS: ${item.veiculos.joinToString(", ")}", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ApartamentoFormScreen(navController: NavController) {
    var numero by remember { mutableStateOf("") }
    var bloco by remember { mutableStateOf("") }
    val vinculadosMoradores = remember { mutableStateListOf<String>() }
    val vinculadosVeiculos = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("REGISTRO DE APARTAMENTO", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(24.dp).verticalScroll(rememberScrollState())) {
            OutlinedTextField(value = numero, onValueChange = { numero = it }, label = { Text("NÚMERO") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = bloco, onValueChange = { bloco = it }, label = { Text("BLOCO") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(24.dp))
            Text("VINCULAR MORADORES", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Button(onClick = { vinculadosMoradores.add("Novo Morador Selecionado") }, modifier = Modifier.padding(vertical = 8.dp), shape = RectangleShape) {
                Icon(Icons.Default.PersonAdd, null)
                Text(" ADICIONAR")
            }

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                vinculadosMoradores.forEach { m ->
                    InputChip(selected = true, onClick = { vinculadosMoradores.remove(m) }, label = { Text(m) }, trailingIcon = { Icon(Icons.Default.Close, null, Modifier.size(16.dp)) })
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("VINCULAR VEÍCULOS", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Button(onClick = { vinculadosVeiculos.add("Placa ABC-1234") }, modifier = Modifier.padding(vertical = 8.dp), shape = RectangleShape) {
                Icon(Icons.Default.DirectionsCar, null)
                Text(" VINCULAR")
            }

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                vinculadosVeiculos.forEach { v ->
                    InputChip(selected = true, onClick = { vinculadosVeiculos.remove(v) }, label = { Text(v) }, trailingIcon = { Icon(Icons.Default.Close, null, Modifier.size(16.dp)) })
                }
            }

            Spacer(Modifier.height(40.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("GRAVAR") }
                Spacer(Modifier.width(12.dp))
                OutlinedButton(onClick = { numero = ""; bloco = ""; vinculadosMoradores.clear(); vinculadosVeiculos.clear() }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("LIMPAR") }
            }
        }
    }
}