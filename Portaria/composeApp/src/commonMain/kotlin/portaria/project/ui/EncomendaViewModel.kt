package portaria.project.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import portaria.project.data.RepositorioRemoto
import portaria.project.domain.Encomenda

class EncomendaViewModel : ViewModel() {
    private val repo = RepositorioRemoto("encomendas")

    var listaEncomendas = mutableStateListOf<Encomenda>()
    var idAtual by mutableStateOf(0)
    var descricao by mutableStateOf("")
    var destinatario by mutableStateOf("")
    var dataRecebimento by mutableStateOf("")
    var isEntregue by mutableStateOf(false)

    fun carregar() = viewModelScope.launch {
        try {
            listaEncomendas.clear()
            listaEncomendas.addAll(repo.buscarTodos<Encomenda>())
        } catch (e: Exception) { println("Erro ao carregar encomendas") }
    }

    fun gravar() = viewModelScope.launch {
        val encomenda = Encomenda(idAtual, descricao, destinatario, dataRecebimento, isEntregue)
        try {
            if (idAtual == 0) repo.inserir(encomenda) else repo.atualizar(idAtual, encomenda)
            limpar()
            carregar()
        } catch (e: Exception) { println("Erro ao gravar encomenda") }
    }

    fun apagar(id: Int) = viewModelScope.launch {
        try {
            repo.apagar(id)
            carregar()
        } catch (e: Exception) { println("Erro ao apagar encomenda") }
    }

    fun carregarParaEdicao(encomenda: Encomenda) {
        idAtual = encomenda.id
        descricao = encomenda.descricao
        destinatario = encomenda.destinatario
        dataRecebimento = encomenda.dataRecebimento
        isEntregue = encomenda.isEntregue
    }

    fun limpar() {
        idAtual = 0; descricao = ""; destinatario = ""; dataRecebimento = ""; isEntregue = false
    }
}