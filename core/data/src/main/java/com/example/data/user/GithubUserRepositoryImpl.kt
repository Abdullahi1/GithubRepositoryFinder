package com.example.data.user

import com.example.data.api.ApiService
import com.example.data.common.sendRequest
import com.example.data.common.tryCatch
import com.example.domain.common.Resource
import com.example.domain.model.GithubSearchUserData
import com.example.domain.model.GithubUserProfileData
import com.example.domain.model.GithubUserRepositoryData
import com.example.domain.user.GithubUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : GithubUserRepository {

    override suspend fun searchGithubUser(query: String): Resource<List<GithubSearchUserData>> =
        withContext(Dispatchers.IO) {
            val result = sendRequest {
                apiService.searchUser(query = query)
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

            val userSearchListResponse = result.getOrNull() ?: return@withContext Resource.error(
                message = "Unable to retrieve data"
            )

            return@withContext Resource.success(data = userSearchListResponse.items?.map {
                GithubSearchUserData(
                    userName = it.login ?: "", imageUrl = it.avatarUrl ?: ""
                )
            } ?: emptyList())
        }

    override suspend fun getGithubUserProfile(username: String): Resource<GithubUserProfileData> =
        withContext(Dispatchers.IO) {
            val result = sendRequest {
                apiService.getGithubUserProfile(username = username)
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

            val githubUserProfile = result.getOrNull() ?: return@withContext Resource.error(
                message = "Unable to retrieve data"
            )
            return@withContext Resource.success(
                GithubUserProfileData(

                    userName = githubUserProfile.login ?: "",
                    fullName = githubUserProfile.name ?: "",
                    bio = githubUserProfile.bio ?: "",
                    location = githubUserProfile.location ?: "",
                    emailAddress = githubUserProfile.email ?: "",
                    imageUrl = githubUserProfile.avatarUrl ?: ""
                )
            )
        }

    override suspend fun getGithubUserRepositories(username: String): Resource<List<GithubUserRepositoryData>> =
        withContext(Dispatchers.IO) {
            val result = sendRequest {
                apiService.getGithubUserRepositories(username = username)
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

            val repositoryList = result.getOrNull() ?: return@withContext Resource.error(
                message = "Unable to retrieve data"
            )


            return@withContext Resource.success(data = repositoryList.map {
                GithubUserRepositoryData(
                    repositoryId = it.id ?: -1,
                    fullName = it.fullName ?: "",
                    githubUrl = it.svnUrl ?: "",
                    topics = it.topics ?: emptyList(),
                    language = it.language ?: "",
                    repositoryDescription = it.description ?: "",
                    watchersCount = it.watchersCount ?: 0,
                    userUrl = it.owner?.htmlUrl ?: "",
                )
            })
        }
}