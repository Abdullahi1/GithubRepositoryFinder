package com.example.users.screen

import com.example.domain.model.GithubUserProfileData

sealed interface UserListUiState {

    data object Loading : UserListUiState

    data class Success(
        val users: List<GithubUserProfileData>,
    ) : UserListUiState {
        fun isEmpty() = users.isEmpty()
    }

    data object Empty : UserListUiState

    data class Error(
        val message: String,
    ) : UserListUiState
}
