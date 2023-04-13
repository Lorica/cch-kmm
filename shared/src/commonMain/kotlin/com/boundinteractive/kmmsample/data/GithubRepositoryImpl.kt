package com.boundinteractive.kmmsample.data

import com.boundinteractive.kmmsample.data.model.Repo
import com.boundinteractive.kmmsample.util.JsonUtil.decodeFromString
import com.boundinteractive.kmmsample.util.JsonUtil.toJson
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
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(Exception::class)
    override suspend fun getRepos(): List<Repo> {
        return client.get("$baseUrl/orgs/melbournecocoa/repos").body()
    }

    override fun getFavourites() = getFavouritesFromStorage().toList()

    override fun addToFavourites(id: Int) {
        val favourites = getFavouritesFromStorage()
        favourites.add(id)

        keyPantry.saveString(FAVOURITES_KEY, favourites.toJson())
    }

    override fun removeFromFavourites(id: Int) {
        val favourites = getFavouritesFromStorage()
        favourites.remove(id)

        keyPantry.saveString(FAVOURITES_KEY, favourites.toJson())
    }

    override fun removeAllFavourites() {
        keyPantry.saveString(FAVOURITES_KEY, emptyArray<Int>().toJson())
    }

    private fun getFavouritesFromStorage() = keyPantry.fetchString(FAVOURITES_KEY)
        ?.decodeFromString<MutableList<Int>>() ?: mutableListOf()
}