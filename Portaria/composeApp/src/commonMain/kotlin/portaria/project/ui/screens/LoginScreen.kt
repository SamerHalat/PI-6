package portaria.project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.Start, // Alinhamento à esquerda é mais moderno
        verticalArrangement = Arrangement.Center
    ) {
        Text("SECURE", fontSize = 12.sp, letterSpacing = 4.sp, color = MaterialTheme.colorScheme.primary)
        Text("ENTRY", fontSize = 48.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("E-MAIL CORPORATIVO") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = senha, onValueChange = { senha = it },
            label = { Text("SENHA") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("ACESSAR SISTEMA", fontWeight = FontWeight.Bold)
        }

        TextButton(
            onClick = { navController.navigate("registro") },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp)
        ) {
            Text("SOLICITAR NOVO ACESSO", color = MaterialTheme.colorScheme.primary)
        }
    }
}