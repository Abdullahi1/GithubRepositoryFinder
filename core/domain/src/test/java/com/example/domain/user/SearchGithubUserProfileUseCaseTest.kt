package com.example.domain.user

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

class SearchGithubUserProfileUseCaseTest {

    private lateinit var searchGithubUserProfileUseCase: SearchGithubUserProfileUseCase
    private val repository: GithubUserRepository = mock()

    @Before
    fun init() {
        searchGithubUserProfileUseCase = SearchGithubUserProfileUseCase(repository)
    }

    @Test
    fun `verify that the searchGithubUserProfile() method in the User repository was called`() {
        runTest {
            // when we invoke the use case
            searchGithubUserProfileUseCase("")

            // then we verify that the repository is called
            verify(repository).searchGithubUserProfile(any())
        }
    }

    @Test
    fun `verify that usecase call returns value on success`() {
        runTest {
            // given that repo search was successful
            whenever(repository.searchGithubUserProfile(any())).thenReturn(
                Resource.success(
                    listOf(
                        FakeDataClass.githubUserProfileData
                    )
                )
            )

            // when we invoke the use case
            val result = searchGithubUserProfileUseCase("")

            // then assert that the call is successful
            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(!result.data.isNullOrEmpty())
        }
    }

    @Test
    fun `verify that usecase call returns error message on failure`() {
        runTest {
            // given that call is not successful
            val errorMessage = "Not Found"
            whenever(repository.searchGithubUserProfile(any())).thenReturn(
                Resource.error(
                    errorMessage
                )
            )

            // when we invoke the use case
            val result = searchGithubUserProfileUseCase("")

            // then assert that the search failed
            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, errorMessage)
        }
    }
}
