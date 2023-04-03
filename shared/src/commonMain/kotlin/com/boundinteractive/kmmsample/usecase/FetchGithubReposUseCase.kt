package com.boundinteractive.kmmsample.usecase

import co.touchlab.kermit.Logger
import com.boundinteractive.kmmsample.data.GithubRepository
import com.boundinteractive.kmmsample.data.model.BasicResult
import com.boundinteractive.kmmsample.data.model.Repo
import com.boundinteractive.kmmsample.data.model.toErrorType

open class FetchGithubReposUseCase(private val githubRepository: GithubRepository) {
    suspend fun invoke(): BasicResult<List<Repo>> {
        return try {
            val result = githubRepository.getRepos()
            BasicResult.Success(result)
        } catch (e: Exception) {
            Logger.e { e.toString() }
            BasicResult.Error(e.toErrorType())
        }
    }
}