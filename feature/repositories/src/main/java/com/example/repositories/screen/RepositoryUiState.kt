package com.example.repositories.screen

import com.example.domain.model.GithubRepositoryData

sealed interface RepositoryUiState {

    data object Loading : RepositoryUiState

    data class Success(
        val repositories: List<GithubRepositoryData>,
    ) : RepositoryUiState {
        fun isEmpty() = repositories.isEmpty()
    }

    data object Empty : RepositoryUiState

    data class Error(
        val message: String,
    ) : RepositoryUiState
}
