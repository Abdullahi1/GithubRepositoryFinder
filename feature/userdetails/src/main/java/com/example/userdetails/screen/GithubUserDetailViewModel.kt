package com.example.userdetails.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.user.GetGithubUserProfileUseCase
import com.example.domain.user.GetGithubUserRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserDetailViewModel @Inject constructor(
    private val getGithubUserProfileUseCase: GetGithubUserProfileUseCase,
    private val getGithubUserRepositoryUseCase: GetGithubUserRepositoryUseCase,
) : ViewModel() {

    private val _userDetailsState = MutableStateFlow<UserDetailsUiState>(UserDetailsUiState.Empty)
    val userDetailsState: StateFlow<UserDetailsUiState> = _userDetailsState


    fun getUserProfile(username: String) {
        _userDetailsState.value = UserDetailsUiState.Loading
        viewModelScope.launch {
            val userProfileResult = getGithubUserProfileUseCase(param = username)
            val userRepositoryResult = getGithubUserRepositoryUseCase(param = username)
            if (userProfileResult.isError() || userProfileResult.data == null) {
                _userDetailsState.value =
                    UserDetailsUiState.Error(message = userProfileResult.message)
                return@launch
            }

            userProfileResult.data?.let {
                _userDetailsState.value = UserDetailsUiState.Success(
                    user = it, repositories = userRepositoryResult.data
                )
            }
        }
    }
}