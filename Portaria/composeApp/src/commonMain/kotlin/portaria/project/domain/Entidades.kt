package portaria.project.domain

import kotlinx.serialization.Serializable

@Serializable
data class Morador(
    val id: Int,
    val nome: String,
    val email: String,
    val dataNascimento: String,
    val isAtivo: Boolean
)

@Serializable
data class Visitante(
    val id: Int,
    val nome: String,
    val rg: String,
    val dataVisita: String,
    val isLiberado: Boolean
)

@Serializable
data class Veiculo(
    val id: Int,
    val placa: String,
    val modelo: String,
    val cor: String,
    val isMorador: Boolean
)

@Serializable
data class Encomenda(
    val id: Int,
    val descricao: String,
    val destinatario: String,
    val dataRecebimento: String,
    val isEntregue: Boolean
)