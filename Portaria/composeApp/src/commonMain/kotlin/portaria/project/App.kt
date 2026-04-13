package portaria.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import portaria.project.ui.*

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val moradorVM: MoradorViewModel = viewModel()
        val visitanteVM: VisitanteViewModel = viewModel()
        val veiculoVM: VeiculoViewModel = viewModel()
        val encomendaVM: EncomendaViewModel = viewModel()

        NavHost(navController, startDestination = "login") {
            composable("login") {
                Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                    Text("Login Portaria", style = MaterialTheme.typography.headlineLarge)
                    OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") })
                    OutlinedTextField(value = "", onValueChange = {}, label = { Text("Senha") }, visualTransformation = PasswordVisualTransformation())
                    Button(onClick = { navController.navigate("home") }) { Text("Entrar") }
                    TextButton(onClick = { navController.navigate("registro") }) { Text("Criar Conta") }
                }
            }

            composable("registro") {
                Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                    Text("Registo", style = MaterialTheme.typography.headlineLarge)
                    Button(onClick = { navController.popBackStack() }) { Text("Voltar") }
                }
            }

            composable("home") {
                Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                    Text("Menu Principal", style = MaterialTheme.typography.headlineMedium)
                    Button(onClick = { moradorVM.carregar(); navController.navigate("morador_lista") }) { Text("Moradores") }
                    Button(onClick = { visitanteVM.carregar(); navController.navigate("visitante_lista") }) { Text("Visitantes") }
                }
            }

            // Exemplo de Ecrã de Listagem (Repetir para os outros 3)
            composable("morador_lista") {
                Column(Modifier.padding(16.dp)) {
                    Text("Lista de Moradores", style = MaterialTheme.typography.titleLarge)
                    Button(onClick = { moradorVM.limpar(); navController.navigate("morador_form") }) { Text("Novo") }
                    LazyColumn {
                        items(moradorVM.listaMoradores) { m ->
                            Card(Modifier.fillMaxWidth().padding(8.dp)) {
                                Row(Modifier.padding(16.dp), Arrangement.SpaceBetween) {
                                    Text(m.nome)
                                    Row {
                                        IconButton(onClick = { moradorVM.carregarParaEdicao(m); navController.navigate("morador_form") }) { Icon(Icons.Default.Edit, "Editar") }
                                        IconButton(onClick = { moradorVM.apagar(m.id) }) { Icon(Icons.Default.Delete, "Apagar") }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            composable("morador_form") {
                Column(Modifier.padding(16.dp)) {
                    OutlinedTextField(value = moradorVM.nome, onValueChange = { moradorVM.nome = it }, label = { Text("Nome") })
                    Button(onClick = { moradorVM.gravar(); navController.popBackStack() }) { Text("Gravar") }
                }
            }
        }
    }
}