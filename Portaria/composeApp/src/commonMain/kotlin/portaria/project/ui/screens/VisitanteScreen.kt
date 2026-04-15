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

data class VisitanteData(val id: String, val nome: String, val doc: String, val dest: String, val status: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitanteListScreen(navController: NavController) {
    var busca by remember { mutableStateOf("") }

    val lista = listOf(
        VisitanteData("1", "JOÃO PEDRO", "123.456.789", "APTO 101", "RECORRENTE"),
        VisitanteData("2", "CARLOS MENDES", "987.654.321", "APTO 504", "VISITA ÚNICA")
    )

    val filtrada = lista.filter {
        it.nome.contains(busca, ignoreCase = true) ||
                it.doc.contains(busca, ignoreCase = true)
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("VISITANTES", fontSize = 14.sp, fontWeight = FontWeight.Bold) }, navigationIcon = { IconButton(onClick = { navController.navigate("home") }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }) },
        floatingActionButton = { FloatingActionButton(onClick = { navController.navigate("visitantes_form") }, shape = RectangleShape) { Icon(Icons.Default.Add, null) } }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {

            // Barra de Pesquisa
            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                placeholder = { Text("Pesquisar visitante ou documento...") },
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
                                Text(item.nome, fontWeight = FontWeight.Black, fontSize = 16.sp)
                                Text("ID: ${item.id} | DOC: ${item.doc} | DEST: ${item.dest}", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                                Text("STATUS: ${item.status}", fontSize = 12.sp, color = if(item.status == "RECORRENTE") Color.Yellow else Color.Cyan)
                            }
                            IconButton(onClick = { navController.navigate("visitantes_form") }) { Icon(Icons.Default.EditNote, null) }
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
fun VisitanteFormScreen(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var documento by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var recorrente by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("REGISTRO DE VISITANTE", fontSize = 14.sp, fontWeight = FontWeight.Bold) }, navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {
            OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("NOME DO VISITANTE") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Row {
                OutlinedTextField(value = documento, onValueChange = { documento = it }, label = { Text("RG / CPF") }, modifier = Modifier.weight(1f))
                Spacer(Modifier.width(12.dp))
                OutlinedTextField(value = destino, onValueChange = { destino = it }, label = { Text("DESTINO (APTO)") }, modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text("VISITANTE RECORRENTE", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Switch(checked = recorrente, onCheckedChange = { recorrente = it })
            }
            Spacer(Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("GRAVAR") }
                Spacer(Modifier.width(12.dp))
                OutlinedButton(onClick = { nome = ""; documento = ""; destino = ""; recorrente = false }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("LIMPAR") }
            }
        }
    }
}