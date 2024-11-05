package com.example.data.repository

import com.example.data.api.ApiService
import com.example.data.common.sendRequest
import com.example.data.common.tryCatch
import com.example.domain.common.Resource
import com.example.domain.model.GithubRepositoryData
import com.example.domain.repository.GithubRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val api: ApiService,
) : GithubRepoRepository {

    override suspend fun searchRepositories(query: String): Resource<List<GithubRepositoryData>> =
        withContext(Dispatchers.IO) {
            val result = sendRequest {
                api.searchRepositories(query = query)
            }

            if (result.isFailure) {
                val exceptionMessage = result.exceptionOrNull()?.let {
                    if (it is HttpException) {
                        val errorMessage = it.response()?.errorBody()?.string() ?: ""
                        val message = when {
                            it.code() == 500 -> {
                                "Internal Server Error"
                            }

                            it.code() == 404 -> {
                                "Not Found"
                            }

                            else -> {
                                tryCatch {
                                    val jsonObject = JSONObject(errorMessage)
                                    jsonObject.getString("message")

                                } ?: "Connection error. Try again"
                            }

                        }

                        message
                    } else {
                        "Connection error. Try again"
                    }
                }
                return@withContext Resource.error(message = exceptionMessage ?: "An error Occurred")
            }

            val searchRepoListResponse = result.getOrNull() ?: return@withContext Resource.error(
                message = "Unable to retrieve data"
            )


            return@withContext Resource.success(data = searchRepoListResponse.items?.map {
                GithubRepositoryData(
                    repositoryId = it.id ?: -1,
                    fullName = it.fullName ?: "",
                    githubUrl = it.svnUrl ?: "",
                    topics = it.topics ?: emptyList(),
                    language = it.language ?: "",
                    repositoryDescription = it.description ?: "",
                    watchersCount = it.watchersCount ?: 0,
                    userUrl = it.owner?.htmlUrl ?: "",
                )
            } ?: emptyList())

        }

}