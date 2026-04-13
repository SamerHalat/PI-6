package portaria.project.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import portaria.project.data.RepositorioRemoto
import portaria.project.domain.Morador

class MoradorViewModel : ViewModel() {
    private val repo = RepositorioRemoto("moradores")
    var listaMoradores = mutableStateListOf<Morador>()
    var idAtual by mutableStateOf(0)
    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var dataNascimento by mutableStateOf("")
    var isAtivo by mutableStateOf(true)

    fun carregar() = viewModelScope.launch {
        listaMoradores.clear()
        listaMoradores.addAll(repo.buscarTodos<Morador>())
    }

    fun gravar() = viewModelScope.launch {
        val obj = Morador(idAtual, nome, email, dataNascimento, isAtivo)
        if (idAtual == 0) repo.inserir(obj) else repo.atualizar(idAtual, obj)
        limpar(); carregar()
    }

    fun apagar(id: Int) = viewModelScope.launch { repo.apagar(id); carregar() }

    fun carregarParaEdicao(m: Morador) {
        idAtual = m.id; nome = m.nome; email = m.email; dataNascimento = m.dataNascimento; isAtivo = m.isAtivo
    }

    fun limpar() { idAtual = 0; nome = ""; email = ""; dataNascimento = ""; isAtivo = true }
}