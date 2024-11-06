package com.example.users.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.user.SearchGithubUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserListViewModel @Inject constructor(
    val searchGithubUserProfileUseCase: SearchGithubUserProfileUseCase,
) : ViewModel() {

    private val _userListState = MutableStateFlow<UserListUiState>(UserListUiState.Empty)
    val userListState: StateFlow<UserListUiState> = _userListState


    fun searchUser(inputText: String) {
        viewModelScope.launch {
            _userListState.value = UserListUiState.Loading
            val result = searchGithubUserProfileUseCase(param = inputText)
            if (result.isSuccess()) {
                _userListState.value = UserListUiState.Success(users = result.data ?: emptyList())
            } else {
                _userListState.value = UserListUiState.Error(result.message)
            }
        }
    }
}