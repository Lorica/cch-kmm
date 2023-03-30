package com.boundinteractive.kmmsample.usecase

import com.boundinteractive.kmmsample.data.GithubRepository
import com.boundinteractive.kmmsample.data.model.Repo

open class FetchGithubReposUseCase(private val githubRepository: GithubRepository) {
    suspend fun invoke(): List<Repo> = githubRepository.getRepos()
}