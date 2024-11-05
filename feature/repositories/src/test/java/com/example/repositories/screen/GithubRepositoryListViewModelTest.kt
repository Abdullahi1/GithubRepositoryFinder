package com.example.repositories.screen

import com.example.domain.common.Resource
import com.example.domain.repository.SearchGithubRepositoryUseCase
import com.example.repositories.common.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever


class GithubRepositoryListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val searchGithubRepositoryUseCase: SearchGithubRepositoryUseCase = mock()

    private lateinit var viewmodel: GithubRepositoryListViewModel

    @Before
    fun init() {
        viewmodel = GithubRepositoryListViewModel(
            searchGithubRepositoryUseCase = searchGithubRepositoryUseCase
        )
    }

    @Test
    fun `state is initially empty`() = runTest {
        Assert.assertEquals(RepositoryUiState.Empty, viewmodel.repositoryState.value)
    }

    @Test
    fun `state is successful when the search search result is successful `() = runTest {

        whenever(searchGithubRepositoryUseCase(any())).thenReturn(Resource.success(emptyList()))

        viewmodel.searchRepository("")

        Assert.assertEquals(
            RepositoryUiState.Success(emptyList()),
            viewmodel.repositoryState.value,
        )
    }

    @Test
    fun `state is error when the search search result fails `() = runTest {

        whenever(searchGithubRepositoryUseCase(any())).thenReturn(Resource.error(""))

        viewmodel.searchRepository("")

        Assert.assertEquals(
            RepositoryUiState.Error(""),
            viewmodel.repositoryState.value,
        )
    }
}