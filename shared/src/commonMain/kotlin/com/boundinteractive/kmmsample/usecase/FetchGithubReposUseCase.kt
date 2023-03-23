package com.boundinteractive.kmmsample.usecase

import com.boundinteractive.kmmsample.data.GithubRepository
import com.boundinteractive.kmmsample.data.model.Repo

class FetchGithubReposUseCase(private val githubRepository: GithubRepository) {
    suspend fun invoke(): List<Repo> = githubRepository.getRepos()
}