package com.boundinteractive.kmmsample.data

import com.boundinteractive.kmmsample.data.model.Repo

interface GithubRepository {
    @Throws(Exception::class)
    suspend fun getRepos(): List<Repo>
    fun getFavourites(): List<Int>
    fun addToFavourites(id: Int)
    fun removeFromFavourites(id: Int)
    fun removeAllFavourites()
}