package com.example.domain.model

data class GithubRepositoryData(
    val repositoryId: Int,
    val fullName: String,
    val githubUrl: String,
    val topics: List<String>,
    val language: String,
    val repositoryDescription: String,
    val starGazersCount: Int,
    val userUrl: String,
    val userImageUrl: String
)
