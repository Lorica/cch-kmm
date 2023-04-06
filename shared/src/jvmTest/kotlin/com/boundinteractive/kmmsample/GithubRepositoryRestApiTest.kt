package com.boundinteractive.kmmsample

import com.boundinteractive.kmmsample.data.GithubRepository
import com.boundinteractive.kmmsample.data.GithubRepositoryImpl
import com.boundinteractive.kmmsample.data.KeyPantry
import io.ktor.client.plugins.*
import io.ktor.http.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith


@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepositoryRestApiTest {
    private lateinit var githubRepository: GithubRepository
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        val port = 8080
        server = MockWebServer()
        server.start(port)
        githubRepository = GithubRepositoryImpl(
            KeyPantry(),
            baseUrl = "http://localhost:$port"
        )
    }

    @Test
    fun `Given Github repositories are fetched, When GithubRepo fetch is success, then list of repositories is returned`() =
        runTest {
            server.enqueue(
                MockResponse().setHeader("content-type", "application/json")
                    .setBody(DummyResponses.repositories)
            )
            val repos = githubRepository.getRepos()

            val request1: RecordedRequest = server.takeRequest()
            assertEquals("/orgs/melbournecocoa/repos", request1.path)

            repos.run {
                assertEquals(2, size)
                assertEquals(9993977, first().id)
                assertEquals(22910628, this[1].id)
            }
        }

    @Test
    fun `Given Github repositories are fetched, when fetch is failure, then error is thrown`() =
        runTest {
            server.enqueue(
                MockResponse()
                    .setResponseCode(HttpStatusCode.InternalServerError.value)
                    .setBody(HttpStatusCode.InternalServerError.description)
            )

            assertFailsWith(ServerResponseException::class) {
                githubRepository.getRepos()
            }
        }

    @After
    fun cleanUp() {
        server.shutdown()
    }
}