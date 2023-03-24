package com.boundinteractive.kmmsample.data

import com.boundinteractive.kmmsample.data.model.Repo

interface GithubRepository {
    suspend fun getRepos(): List<Repo>
    fun getFavourites(): Array<Int>
    fun addToFavourites(id: Int)
    fun removeFromFavourites(id: Int)
    fun removeAllFavourites()
}