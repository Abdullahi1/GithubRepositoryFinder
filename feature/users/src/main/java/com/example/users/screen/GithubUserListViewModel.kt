package com.example.users.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.user.SearchGithubUserProfileRTUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserListViewModel @Inject constructor(
    val searchGithubUserProfileRTUseCase: SearchGithubUserProfileRTUseCase,
) : ViewModel() {

    private val _userListState = MutableStateFlow<UserListUiState>(UserListUiState.Empty)
    val userListState: StateFlow<UserListUiState> = _userListState


    fun searchUser(inputText: String) {
        viewModelScope.launch {
            _userListState.value = UserListUiState.Loading
            val result = searchGithubUserProfileRTUseCase(param = inputText)
            if (result.isSuccess()) {
                _userListState.value = UserListUiState.Success(
                    users =(result.data?.distinctUntilChanged() ?: flowOf()).stateIn(viewModelScope)
                )
            } else {
                _userListState.value = UserListUiState.Error(result.message)
            }
        }
    }
}