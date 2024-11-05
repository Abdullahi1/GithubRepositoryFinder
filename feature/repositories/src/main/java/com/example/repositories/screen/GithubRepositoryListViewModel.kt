package com.example.repositories.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.SearchGithubRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubRepositoryListViewModel @Inject constructor(
    val searchGithubRepositoryUseCase: SearchGithubRepositoryUseCase,
) : ViewModel() {

    private val _repositoryState = MutableStateFlow<RepositoryUiState>(RepositoryUiState.Empty)
    val repositoryState: StateFlow<RepositoryUiState> = _repositoryState


    fun searchRepository(inputText: String) {
        viewModelScope.launch {
            _repositoryState.value = RepositoryUiState.Loading
            val result = searchGithubRepositoryUseCase(param = inputText)
            if (result.isSuccess()){
                _repositoryState.value = RepositoryUiState.Success(repositories = result.data ?: emptyList())
            }else{
                _repositoryState.value = RepositoryUiState.Error(result.message)
            }
        }
    }
}