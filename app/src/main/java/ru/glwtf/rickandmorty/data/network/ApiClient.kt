package ru.glwtf.rickandmorty.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import ru.glwtf.rickandmorty.data.model.CharactersDto
import javax.inject.Inject

class ApiClient @Inject constructor() {

    suspend fun getCharacters(page: Int) = withContext(Dispatchers.IO) {
        client.get("$URL$page").body<CharactersDto>()
    }

    companion object {
        private val json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
        private const val URL = "https://rickandmortyapi.com/api/character/?page="
        private val client = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }
}