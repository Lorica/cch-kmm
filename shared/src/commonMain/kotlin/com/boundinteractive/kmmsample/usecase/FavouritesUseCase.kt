package com.boundinteractive.kmmsample.usecase

import com.boundinteractive.kmmsample.data.GithubRepository

open class FavouritesUseCase(private val githubRepository: GithubRepository) {
    fun fetch(): List<Int> = githubRepository.getFavourites()

    fun add(id: Int) = githubRepository.addToFavourites(id)

    fun remove(id: Int) = githubRepository.removeFromFavourites(id)

    fun removeAll() = githubRepository.removeAllFavourites()
}