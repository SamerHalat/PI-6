package portaria.project.domain

import kotlinx.serialization.Serializable

@Serializable
data class Morador(val id: String? = null, val nome: String, val apto: String, val bloco: String)

@Serializable
data class Visitante(val id: String? = null, val nome: String, val rg: String, val destino: String)

@Serializable
data class Veiculo(val id: String? = null, val placa: String, val modelo: String, val cor: String)

@Serializable
data class Encomenda(val id: String? = null, val descricao: String, val destinatario: String, val status: String)