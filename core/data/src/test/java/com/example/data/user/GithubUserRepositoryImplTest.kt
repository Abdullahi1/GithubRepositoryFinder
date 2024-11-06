package com.example.data.user

import com.example.data.api.ApiService
import com.example.data.common.FakeResponseData
import com.example.data.model.repository.SearchRepositoryListResponse
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response


class GithubUserRepositoryImplTest {
    private lateinit var repository: GithubUserRepositoryImpl
    private val api: ApiService = mock()

    @Before
    fun init() {
        repository = GithubUserRepositoryImpl(
            apiService = api
        )
    }

    @Test
    fun `check that  searchUser() return a success on request`() = runTest {

        //given that the api request is successful
        whenever(api.searchUser(any())).thenReturn(FakeResponseData.fakeGithubUserSearchResponse)

        val result = repository.searchGithubUser("q")


        Assert.assertTrue(result.isSuccess())
        Assert.assertTrue(result.data != null)
    }

    @Test
    fun `check that  searchUser() handles error code 404 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.searchUser(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.searchGithubUser("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Not Found")
        }

    @Test
    fun `check that searchUser() handles error code 500 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.searchUser(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        500, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.searchGithubUser("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Internal Server Error")
        }


    @Test
    fun `check that  getGithubUserProfile() return a success on request`() = runTest {

        //given that the api request is successful
        whenever(api.getGithubUserProfile(any())).thenReturn(FakeResponseData.fakeGithubUserProfileResponse)

        val result = repository.getGithubUserProfile("q")


        Assert.assertTrue(result.isSuccess())
        Assert.assertTrue(result.data != null)
    }

    @Test
    fun `check that  getGithubUserProfile() handles error code 404 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.getGithubUserProfile(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.getGithubUserProfile("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Not Found")
        }

    @Test
    fun `check that getGithubUserProfile() handles error code 500 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.getGithubUserProfile(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        500, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.getGithubUserProfile("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Internal Server Error")
        }


    @Test
    fun `check that  getGithubUserRepositories() return a success on request`() = runTest {

        //given that the api request is successful
        whenever(api.getGithubUserRepositories(any())).thenReturn(listOf(FakeResponseData.fakeGithubUserRepositoryResponse))

        val result = repository.getGithubUserRepositories("q")


        Assert.assertTrue(result.isSuccess())
        Assert.assertTrue(result.data != null)
    }

    @Test
    fun `check that  getGithubUserRepositories() handles error code 404 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.getGithubUserRepositories(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.getGithubUserRepositories("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Not Found")
        }

    @Test
    fun `check that getGithubUserRepositories() handles error code 500 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.getGithubUserRepositories(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        500, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.getGithubUserRepositories("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Internal Server Error")
        }

    @Test
    fun `check that searchGithubUserProfile() returns success on successful call`() =
        runTest {

            //given that the api requests is successful
            whenever(api.searchUser(any())).thenReturn(FakeResponseData.fakeGithubUserSearchResponse)
            whenever(api.getGithubUserProfile(any())).thenReturn(FakeResponseData.fakeGithubUserProfileResponse)

            val result = repository.searchGithubUserProfile("q")


            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(result.data != null)
        }

    @Test
    fun `check that searchGithubUserProfile() returns success on even if the second api request fails`() =
        runTest {

            //given that the searchUser api request is successful and getGithubUserProfile api request fails
            whenever(api.searchUser(any())).thenReturn(FakeResponseData.fakeGithubUserSearchResponse)
            whenever(api.getGithubUserProfile(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )
            val result = repository.searchGithubUserProfile("q")


            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(result.data != null)
        }

    @Test
    fun `check that searchGithubUserProfile() returns error if the searchUser() call fails`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.searchUser(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.searchGithubUserProfile("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Not Found")
        }


    @Test
    fun `check that searchGithubUserProfileRT() returns success on successful call`() =
        runTest {

            //given that the api requests is successful
            whenever(api.searchUser(any())).thenReturn(FakeResponseData.fakeGithubUserSearchResponse)
            whenever(api.getGithubUserProfile(any())).thenReturn(FakeResponseData.fakeGithubUserProfileResponse)

            val result = repository.searchGithubUserProfileRT("q")


            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(result.data != null)
        }

    @Test
    fun `check that searchGithubUserProfileRT() returns success on even if the second api request fails`() =
        runTest {

            //given that the searchUser api request is successful and getGithubUserProfile api request fails
            whenever(api.searchUser(any())).thenReturn(FakeResponseData.fakeGithubUserSearchResponse)
            whenever(api.getGithubUserProfile(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )
            val result = repository.searchGithubUserProfileRT("q")


            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(result.data != null)
        }

    @Test
    fun `check that searchGithubUserProfileRT() returns error if the searchUser() call fails`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.searchUser(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.searchGithubUserProfileRT("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Not Found")
        }

}