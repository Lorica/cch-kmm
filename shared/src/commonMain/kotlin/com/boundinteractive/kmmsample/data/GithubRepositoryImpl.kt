package com.boundinteractive.kmmsample.data

import com.boundinteractive.kmmsample.data.model.Repo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class GithubRepositoryImpl(
    private val keyPantry: KeyPantry,
    private val baseUrl: String = "https://api.github.com"
) : GithubRepository {
    companion object {
        private const val FAVOURITES_KEY = "favourites"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(Exception::class)
    override suspend fun getRepos(): List<Repo> {
        val response = client.get("${baseUrl}/orgs/melbournecocoa/repos")
        if (response.status.isSuccess()) {
            return response.body()
        } else {
            throw ServerResponseException(response, response.bodyAsText())
        }
    }

    override fun getFavourites() = keyPantry.fetchIntArray(FAVOURITES_KEY).toList()

    override fun addToFavourites(id: Int) {
        val favourites = keyPantry.fetchIntArray(FAVOURITES_KEY).toMutableList()
        favourites.add(id)

        keyPantry.saveIntArray(FAVOURITES_KEY, favourites.toTypedArray())
    }

    override fun removeFromFavourites(id: Int) {
        val favourites = keyPantry.fetchIntArray(FAVOURITES_KEY).toMutableList()
        favourites.remove(id)

        keyPantry.saveIntArray(FAVOURITES_KEY, favourites.toTypedArray())
    }

    override fun removeAllFavourites() {
        keyPantry.saveIntArray(FAVOURITES_KEY, emptyArray())
    }
}