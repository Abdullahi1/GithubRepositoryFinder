package com.example.domain.repository

import com.example.domain.common.Resource
import com.example.domain.model.GithubRepositoryData

interface GithubRepoRepository {

    suspend fun searchRepositories(query: String): Resource<List<GithubRepositoryData>>
}