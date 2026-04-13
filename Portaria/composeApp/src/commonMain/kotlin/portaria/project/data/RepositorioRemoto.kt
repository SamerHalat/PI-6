package portaria.project.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class RepositorioRemoto(val colecao: String) {

    @PublishedApi
    internal val urlBase: String = "https://69dd6d4684f912a264050de6.mockapi.io/$colecao"

    @PublishedApi
    internal val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    suspend inline fun <reified T> buscarTodos(): List<T> {
        return client.get(urlBase).body()
    }

    suspend inline fun <reified T> inserir(obj: T) {
        client.post(urlBase) {
            contentType(ContentType.Application.Json)
            setBody(obj)
        }
    }

    suspend inline fun <reified T> atualizar(id: Int, obj: T) {
        client.put("$urlBase/$id") {
            contentType(ContentType.Application.Json)
            setBody(obj)
        }
    }

    suspend fun apagar(id: Int) {
        client.delete("$urlBase/$id")
    }
}