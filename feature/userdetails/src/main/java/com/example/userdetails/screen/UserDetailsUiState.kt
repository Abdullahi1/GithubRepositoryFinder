package com.example.userdetails.screen

import com.example.domain.model.GithubUserProfileData
import com.example.domain.model.GithubUserRepositoryData

sealed interface UserDetailsUiState {

    data object Loading : UserDetailsUiState

    data class Success(
        val user: GithubUserProfileData,
        val repositories: List<GithubUserRepositoryData>?
    ) : UserDetailsUiState {
        fun isNullOrEmpty() = repositories.isNullOrEmpty()
    }

    data object Empty : UserDetailsUiState

    data class Error(
        val message: String,
    ) : UserDetailsUiState
}
