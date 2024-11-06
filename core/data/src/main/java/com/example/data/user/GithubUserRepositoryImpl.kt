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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun searchGithubUserProfile(query: String): Resource<List<GithubUserProfileData>> =
        withContext(Dispatchers.IO) {
            val usersResult = searchGithubUser(query = query)
            if (usersResult.isError()) {
                return@withContext Resource.error(message = usersResult.message)
            }

            val userProfileResult = usersResult.data?.map {
                val result = getGithubUserProfile(username = it.userName)
                if (result.isSuccess() && result.data != null) {
                    result.data!!
                } else {
                    GithubUserProfileData(
                        userName = it.userName,
                        imageUrl = it.imageUrl,
                        fullName = it.userName,
                        bio = "",
                        location = "",
                        emailAddress = "",
                        followerCount = 0,
                        followingCount = 0,
                        createdAt = "",
                        updateAt = "",
                        htmlUrl = "",
                    )
                }
            }

            return@withContext Resource.success(userProfileResult ?: emptyList())
        }

    override suspend fun searchGithubUserProfileRT(query: String): Resource<Flow<List<GithubUserProfileData>>> =
        withContext(Dispatchers.IO) {
            val list = mutableMapOf<String, GithubUserProfileData>()
            val usersResult = searchGithubUser(query = query)
            if (usersResult.isError()) {
                return@withContext Resource.error(message = usersResult.message)
            }

            return@withContext Resource.success(flow<List<GithubUserProfileData>> {
                if (usersResult.data.isNullOrEmpty()){
                    emit(list.values.toList())
                }else {
                    usersResult.data?.forEach {
                        val result = getGithubUserProfile(username = it.userName)
                        val profileData = if (result.isSuccess() && result.data != null) {
                            result.data!!
                        } else {
                            GithubUserProfileData(
                                userName = it.userName,
                                imageUrl = it.imageUrl,
                                fullName = it.userName,
                                bio = "",
                                location = "",
                                emailAddress = "",
                                followerCount = 0,
                                followingCount = 0,
                                createdAt = "",
                                updateAt = "",
                                htmlUrl = "",
                            )
                        }
                        list[profileData.userName] = profileData
                        list.replace(profileData.userName, profileData)
                        emit(list.values.toList())
                    }
                }
            })
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
                return@withContext Resource.error(
                    message = exceptionMessage ?: "An error Occurred"
                )
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
                    imageUrl = githubUserProfile.avatarUrl ?: "",
                    followerCount = githubUserProfile.followers ?: 0,
                    followingCount = githubUserProfile.following ?: 0,
                    createdAt = githubUserProfile.createdAt ?: "",
                    updateAt = githubUserProfile.updatedAt ?: "",
                    htmlUrl = githubUserProfile.htmlUrl ?: "",
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
                return@withContext Resource.error(
                    message = exceptionMessage ?: "An error Occurred"
                )
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
                    watchersCount = it.watchers ?: 0,
                    userUrl = it.owner?.htmlUrl ?: "",
                    starGazersCount = it.stargazersCount ?: 0,
                    createdAt = it.createdAt ?: "",
                    updatedAt = it.updatedAt ?: ""
                )
            })
        }
}