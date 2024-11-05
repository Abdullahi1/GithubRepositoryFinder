package com.example.data.repository

import com.example.data.api.ApiService
import com.example.data.common.FakeResponseData
import com.example.data.model.repository.SearchRepositoryListResponse
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

internal class GithubRepoRepositoryImplTest {

    private lateinit var repository: GithubRepoRepositoryImpl
    private val api: ApiService = mock()

    @Before
    fun init() {
        repository = GithubRepoRepositoryImpl(
            api = api
        )
    }

    @Test
    fun `check that  searchRepositories() return a success on request`() = runTest {

        //given that the api request is successful
        whenever(api.searchRepositories(any())).thenReturn(FakeResponseData.fakeGithubRepoSearchResponse)

        val result = repository.searchRepositories("q")


        Assert.assertTrue(result.isSuccess())
        Assert.assertTrue(result.data != null)
    }

    @Test
    fun `check that  searchRepositories() function handles error code 404 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.searchRepositories(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        404, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.searchRepositories("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Not Found")
        }

    @Test
    fun `check that searchRepositories() function handles error code 500 request and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception
            whenever(api.searchRepositories(any())).thenThrow(
                HttpException(
                    Response.error<SearchRepositoryListResponse>(
                        500, "a".toResponseBody("application/json".toMediaType())
                    )
                )
            )

            val result = repository.searchRepositories("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Internal Server Error")
        }

    @Test
    fun `check that searchRepositories() function handles any exception and also send the proper message`() =
        runTest {

            //given that the api request fails with an exception

            given(api.searchRepositories(any())).willAnswer {
                throw IOException()
            }

            val result = repository.searchRepositories("q")


            Assert.assertTrue(result.isError())
            Assert.assertEquals(result.message, "Connection error. Try again")
        }
}