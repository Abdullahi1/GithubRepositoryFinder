package com.example.users.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.GithubUserProfileData
import com.example.domain.user.SearchGithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserListViewModel @Inject constructor(
    val searchGithubUserUseCase: SearchGithubUserUseCase,
) : ViewModel() {

    private val _userListState = MutableStateFlow<UserListUiState>(UserListUiState.Empty)
    val userListState: StateFlow<UserListUiState> = _userListState


    fun searchUser(inputText: String) {
        viewModelScope.launch {
            _userListState.value = UserListUiState.Loading
            val result = searchGithubUserUseCase(param = inputText)
            if (result.isSuccess()) {
                _userListState.value = UserListUiState.Success(users = result.data?.map {
                    GithubUserProfileData(
                        userName = it.userName,
                        imageUrl = it.imageUrl,
                        fullName = it.userName,
                        bio = "",
                        location = "",
                        emailAddress = ""

                    )
                } ?: emptyList())
            } else {
                _userListState.value = UserListUiState.Error(result.message)
            }
        }
    }
}