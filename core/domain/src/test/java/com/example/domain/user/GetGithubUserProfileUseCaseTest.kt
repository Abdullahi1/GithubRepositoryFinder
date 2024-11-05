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

class GetGithubUserProfileUseCaseTest {

    private lateinit var getGithubUserProfileUseCase: GetGithubUserProfileUseCase
    private val repository: GithubUserRepository = mock()

    @Before
    fun init() {
        getGithubUserProfileUseCase = GetGithubUserProfileUseCase(repository)
    }

    @Test
    fun `verify that the getGithubUserProfile() method in the GithubRepo repository was called`() {
        runTest {
            // when we invoke the use case
            getGithubUserProfileUseCase("")

            // then we verify that the repository is called
            verify(repository).getGithubUserProfile(any())
        }
    }

    @Test
    fun `verify that repository call returns value on success`() {
        runTest {
            // given that repo search was successful
            whenever(repository.getGithubUserProfile(any())).thenReturn(
                Resource.success(FakeDataClass.githubUserProfileData)
            )

            // when we invoke the use case
            val result = getGithubUserProfileUseCase("")

            // then assert that the call is successful
            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(result.data != null)
        }
    }

    @Test
    fun `verify that repository call returns error message on failure`() {
        runTest {
            // given that call is not successful
            val errorMessage = "Not Found"
            whenever(repository.getGithubUserProfile(any())).thenReturn(Resource.error(errorMessage))

            // when we invoke the use case
            val result = getGithubUserProfileUseCase("")

            // then assert that the search failed
            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, errorMessage)
        }
    }
}
