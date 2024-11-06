package com.example.users.screen

import com.example.domain.model.GithubUserProfileData
import kotlinx.coroutines.flow.StateFlow

sealed interface UserListUiState {

    data object Loading : UserListUiState

    data class Success(
        val users: StateFlow<List<GithubUserProfileData>>,
    ) : UserListUiState {

    }

    data object Empty : UserListUiState

    data class Error(
        val message: String,
    ) : UserListUiState
}
