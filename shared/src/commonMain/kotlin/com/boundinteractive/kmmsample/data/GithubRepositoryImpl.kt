package com.boundinteractive.kmmsample.data

import com.boundinteractive.kmmsample.data.model.Repo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GithubRepositoryImpl(private val keyPantry: KeyPantry) : GithubRepository {
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getRepos(): List<Repo> {
        val response = client.get("https://api.github.com/orgs/melbournecocoa/repos")
        return response.body()
    }

    override fun getFavourites() = keyPantry.fetchFavourites()

    override fun addToFavourites(id: Int) = keyPantry.saveFavourites(id)

    override fun removeFromFavourites(id: Int) = keyPantry.removeFavourites(id)
}