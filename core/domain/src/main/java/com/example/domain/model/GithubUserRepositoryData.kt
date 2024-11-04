package com.example.domain.model

data class GithubUserRepositoryData(
    val repositoryId: Int,
    val fullName: String,
    val githubUrl: String,
    val topics: List<String>,
    val language: String,
    val repositoryDescription: String,
    val watchersCount: Int,
    val userUrl: String
)
