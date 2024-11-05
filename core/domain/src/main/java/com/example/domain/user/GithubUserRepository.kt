package com.example.domain.user

import com.example.domain.common.Resource
import com.example.domain.model.GithubSearchUserData
import com.example.domain.model.GithubUserRepositoryData

interface GithubUserRepository {

    suspend fun searchGithubUser(query: String): Resource<List<GithubSearchUserData>>

    suspend fun getGithubUserProfile(username: String): Resource<GithubSearchUserData>

    suspend fun getGithubUserRepositories(username: String): Resource<List<GithubUserRepositoryData>>
}