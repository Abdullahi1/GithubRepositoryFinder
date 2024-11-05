package com.example.data.api

import com.example.data.model.repository.SearchRepositoryListResponse
import com.example.data.model.user.GithubUserProfileResponse
import com.example.data.model.user.GithubUserRepositoryResponse
import com.example.data.model.user.SearchUserListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchRepositoryListResponse

    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): SearchUserListResponse

    @GET("users/{username}")
    suspend fun getGithubUserProfile(@Path("username") username: String): GithubUserProfileResponse

    @GET("users/{username}/repos")
    suspend fun getGithubUserRepositories(@Path("username") username: String): List<GithubUserRepositoryResponse>

}