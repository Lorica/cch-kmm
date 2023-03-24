package com.boundinteractive.kmmsample.usecase

import com.boundinteractive.kmmsample.data.GithubRepository

class FavouritesUseCase(private val githubRepository: GithubRepository) {
    fun fetch(): Array<Int> = githubRepository.getFavourites()

    fun add(id: Int) = githubRepository.addToFavourites(id)

    fun remove(id: Int) = githubRepository.removeFromFavourites(id)

    fun removeAll() = githubRepository.removeAllFavourites()
}