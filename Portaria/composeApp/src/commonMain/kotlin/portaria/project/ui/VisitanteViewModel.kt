package portaria.project.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import portaria.project.data.RepositorioRemoto
import portaria.project.domain.Visitante

class VisitanteViewModel : ViewModel() {
    private val repo = RepositorioRemoto("visitantes")

    var listaVisitantes = mutableStateListOf<Visitante>()
    var idAtual by mutableStateOf(0)
    var nome by mutableStateOf("")
    var rg by mutableStateOf("")
    var dataVisita by mutableStateOf("")
    var isLiberado by mutableStateOf(false)

    fun carregar() = viewModelScope.launch {
        try {
            val dados = repo.buscarTodos<Visitante>()
            listaVisitantes.clear()
            listaVisitantes.addAll(dados)
        } catch (e: Exception) { println("Erro ao carregar visitantes") }
    }

    fun gravar() = viewModelScope.launch {
        val visitante = Visitante(idAtual, nome, rg, dataVisita, isLiberado)
        try {
            if (idAtual == 0) repo.inserir(visitante) else repo.atualizar(idAtual, visitante)
            limpar()
            carregar()
        } catch (e: Exception) { println("Erro ao gravar visitante") }
    }

    fun apagar(id: Int) = viewModelScope.launch {
        try {
            repo.apagar(id)
            carregar()
        } catch (e: Exception) { println("Erro ao apagar visitante") }
    }

    fun carregarParaEdicao(visitante: Visitante) {
        idAtual = visitante.id
        nome = visitante.nome
        rg = visitante.rg
        dataVisita = visitante.dataVisita
        isLiberado = visitante.isLiberado
    }

    fun limpar() {
        idAtual = 0; nome = ""; rg = ""; dataVisita = ""; isLiberado = false
    }
}