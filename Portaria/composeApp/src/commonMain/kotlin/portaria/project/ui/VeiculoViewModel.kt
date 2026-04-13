package portaria.project.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import portaria.project.data.RepositorioRemoto
import portaria.project.domain.Veiculo

class VeiculoViewModel : ViewModel() {
    private val repo = RepositorioRemoto("veiculos") // Usa o RepositorioRemoto genérico

    var listaVeiculos = mutableStateListOf<Veiculo>()
    var idAtual by mutableStateOf(0)
    var placa by mutableStateOf("")
    var modelo by mutableStateOf("")
    var cor by mutableStateOf("")
    var isMorador by mutableStateOf(true)

    fun carregar() = viewModelScope.launch {
        try {
            listaVeiculos.clear()
            listaVeiculos.addAll(repo.buscarTodos<Veiculo>())
        } catch (e: Exception) { println("Erro ao carregar veículos") }
    }

    fun gravar() = viewModelScope.launch {
        val veiculo = Veiculo(idAtual, placa, modelo, cor, isMorador)
        try {
            if (idAtual == 0) repo.inserir(veiculo) else repo.atualizar(idAtual, veiculo)
            limpar()
            carregar()
        } catch (e: Exception) { println("Erro ao gravar veículo") }
    }

    fun apagar(id: Int) = viewModelScope.launch {
        try {
            repo.apagar(id)
            carregar()
        } catch (e: Exception) { println("Erro ao apagar veículo") }
    }

    fun carregarParaEdicao(veiculo: Veiculo) {
        idAtual = veiculo.id
        placa = veiculo.placa
        modelo = veiculo.modelo
        cor = veiculo.cor
        isMorador = veiculo.isMorador
    }

    fun limpar() {
        idAtual = 0; placa = ""; modelo = ""; cor = ""; isMorador = true
    }
}