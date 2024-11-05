package com.example.domain.repository


import com.example.domain.common.FakeDataClass
import com.example.domain.common.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class SearchGithubRepositoryUseCaseTest {

    private lateinit var searchGithubRepositoryUseCase: SearchGithubRepositoryUseCase
    private val repository: GithubRepoRepository = mock()

    @Before
    fun init() {
        searchGithubRepositoryUseCase = SearchGithubRepositoryUseCase(repository)
    }

    @Test
    fun `verify that the searchRepositories() method in the GithubRepo repository was called`() {
        runTest {
            // when we invoke the use case
            searchGithubRepositoryUseCase("")

            // then we verify that the repository is called
            verify(repository).searchRepositories(any())
        }
    }

    @Test
    fun `verify that repository call returns value on success`() {
        runTest {
            // given that repo search was successful
            whenever(repository.searchRepositories(any())).thenReturn(
                Resource.success(
                    listOf(
                        FakeDataClass.repositoryData
                    )
                )
            )

            // when we invoke the use case
            val result = searchGithubRepositoryUseCase("")

            // then assert that the call is successful
            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(!result.data.isNullOrEmpty())
        }
    }

    @Test
    fun `verify that repository call returns error message on failure`() {
        runTest {
            // given that call is not successful
            val errorMessage = "Not Found"
            whenever(repository.searchRepositories(any()))
                .thenReturn(Resource.error(errorMessage))

            // when we invoke the use case
            val result = searchGithubRepositoryUseCase("")

            // then assert that the search failed
            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, errorMessage)
        }
    }
}
