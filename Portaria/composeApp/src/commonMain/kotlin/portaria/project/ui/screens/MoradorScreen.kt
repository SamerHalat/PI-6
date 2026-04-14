package portaria.project.ui.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Classe visual temporária
data class MoradorUI(val id: Int, val nome: String, val apto: String, val bloco: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoradorScreen(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var apto by remember { mutableStateOf("") }
    var bloco by remember { mutableStateOf("") }
    val moradores = remember { mutableStateListOf<MoradorUI>() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Gestão de Moradores", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // O 'popBackStack' agora funciona!
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {

            // FORMULÁRIO MODERNO (Requisito 'h')
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Novo Registro", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome Completo") }, modifier = Modifier.fillMaxWidth())
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        OutlinedTextField(value = apto, onValueChange = { apto = it }, label = { Text("Apartamento") }, modifier = Modifier.weight(1f))
                        Spacer(Modifier.width(8.dp))
                        OutlinedTextField(value = bloco, onValueChange = { bloco = it }, label = { Text("Bloco") }, modifier = Modifier.weight(1f))
                    }

                    Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                        Button(onClick = {
                            if(nome.isNotBlank()) moradores.add(MoradorUI(moradores.size + 1, nome, apto, bloco))
                        }, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.Save, null)
                            Text(" Gravar")
                        }
                        Spacer(Modifier.width(8.dp))
                        // BOTÃO LIMPAR (Exigência do PDF)
                        OutlinedButton(onClick = { nome = ""; apto = ""; bloco = "" }, modifier = Modifier.weight(1f)) {
                            Text("Limpar")
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Moradores Cadastrados", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            // LISTAGEM EM CARDS (Requisitos 'i', 'j', 'k')
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
                items(moradores) { item ->
                    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                        ListItem(
                            headlineContent = { Text(item.nome, fontWeight = FontWeight.Bold) },
                            supportingContent = {
                                // Mostrando 3 campos por card
                                Text("Apto: ${item.apto} | Bloco: ${item.bloco} | ID: ${item.id}")
                            },
                            trailingContent = {
                                Row {
                                    IconButton(onClick = { /* Abre formulário para Editar */ }) {
                                        Icon(Icons.Default.Edit, null, tint = MaterialTheme.colorScheme.primary)
                                    }
                                    IconButton(onClick = { moradores.remove(item) }) {
                                        Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}