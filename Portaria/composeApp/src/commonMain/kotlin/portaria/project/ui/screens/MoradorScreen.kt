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


data class MoradorData(val id: String, val nome: String, val apto: String, val bloco: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoradorListScreen(navController: NavController) {
    var busca by remember { mutableStateOf("") }
    val lista = listOf(
        MoradorData("1", "SAMER NASSIR HALAT", "101", "A"),
        MoradorData("2", "ANA COSTA", "202", "B")
    )
    val filtrada = lista.filter { it.nome.contains(busca, ignoreCase = true) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("MORADORES", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("moradores_form") },
                shape = RectangleShape
            ) { Icon(Icons.Default.Add, null) }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(24.dp)) {
            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                placeholder = { Text("Pesquisar por nome...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape
            )
            Spacer(Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filtrada) { m ->
                    Box(modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outline).padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(m.nome, fontWeight = FontWeight.Black)
                                Text("APTO: ${m.apto} | BLOCO: ${m.bloco}", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                            }
                            IconButton(onClick = { navController.navigate("moradores_form") }) { Icon(Icons.Default.EditNote, null) }
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
fun MoradorFormScreen(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var apto by remember { mutableStateOf("") }
    var bloco by remember { mutableStateOf("") }
    var ativo by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("REGISTRO DE MORADOR", fontSize = 14.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {
            OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("NOME COMPLETO") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Row {
                OutlinedTextField(value = apto, onValueChange = { apto = it }, label = { Text("APARTAMENTO") }, modifier = Modifier.weight(1f))
                Spacer(Modifier.width(12.dp))
                OutlinedTextField(value = bloco, onValueChange = { bloco = it }, label = { Text("BLOCO") }, modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text("ACESSO ATIVO", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Switch(checked = ativo, onCheckedChange = { ativo = it })
            }
            Spacer(Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("GRAVAR") }
                Spacer(Modifier.width(12.dp))
                OutlinedButton(onClick = { nome = ""; apto = ""; bloco = ""; ativo = true }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("LIMPAR") }
            }
        }
    }
}