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

data class EncomendaData(val id: String, val descricao: String, val destinatario: String, val local: String, val status: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncomendaListScreen(navController: NavController) {
    var busca by remember { mutableStateOf("") }

    val lista = listOf(
        EncomendaData("1", "CAIXA AMAZON", "SAMER", "PRATELEIRA A", "AGUARDANDO RETIRADA"),
        EncomendaData("2", "CARTA REGISTRADA", "MARIA (202)", "GAVETA 2", "ENTREGUE")
    )

    // Filtro dinâmico
    val filtrada = lista.filter {
        it.descricao.contains(busca, ignoreCase = true) ||
                it.destinatario.contains(busca, ignoreCase = true)
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("ENCOMENDAS", fontSize = 14.sp, fontWeight = FontWeight.Bold) }, navigationIcon = { IconButton(onClick = { navController.navigate("home") }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }) },
        floatingActionButton = { FloatingActionButton(onClick = { navController.navigate("encomendas_form") }, shape = RectangleShape) { Icon(Icons.Default.Add, null) } }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {

            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                placeholder = { Text("Pesquisar encomenda ou destinatário...") },
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
                                Text(item.descricao, fontWeight = FontWeight.Black, fontSize = 16.sp)
                                Text("ID: ${item.id} | DESTINATÁRIO: ${item.destinatario} | LOCAL: ${item.local}", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                                Text("STATUS: ${item.status}", fontSize = 12.sp, color = if(item.status == "ENTREGUE") Color.Green else Color.Yellow)
                            }
                            IconButton(onClick = { navController.navigate("encomendas_form") }) { Icon(Icons.Default.EditNote, null) }
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
fun EncomendaFormScreen(navController: NavController) {
    var descricao by remember { mutableStateOf("") }
    var destinatario by remember { mutableStateOf("") }
    var local by remember { mutableStateOf("") }
    var entregue by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("REGISTRO DE ENCOMENDA", fontSize = 14.sp, fontWeight = FontWeight.Bold) }, navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp)) {
            OutlinedTextField(value = descricao, onValueChange = { descricao = it }, label = { Text("DESCRIÇÃO DA ENCOMENDA") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Row {
                OutlinedTextField(value = destinatario, onValueChange = { destinatario = it }, label = { Text("DESTINATÁRIO") }, modifier = Modifier.weight(1f))
                Spacer(Modifier.width(12.dp))
                OutlinedTextField(value = local, onValueChange = { local = it }, label = { Text("LOCALIZAÇÃO (PRATELEIRA)") }, modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text("ENTREGUE AO MORADOR?", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Switch(checked = entregue, onCheckedChange = { entregue = it })
            }
            Spacer(Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("GRAVAR") }
                Spacer(Modifier.width(12.dp))
                OutlinedButton(onClick = { descricao = ""; destinatario = ""; local = ""; entregue = false }, modifier = Modifier.weight(1f).height(56.dp), shape = RectangleShape) { Text("LIMPAR") }
            }
        }
    }
}