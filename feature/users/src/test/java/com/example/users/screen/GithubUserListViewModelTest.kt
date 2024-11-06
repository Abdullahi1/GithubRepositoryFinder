package com.example.users.screen

import com.example.domain.common.Resource
import com.example.domain.user.SearchGithubUserProfileRTUseCase
import com.example.users.common.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class GithubUserListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val searchGithubUserUseCase: SearchGithubUserProfileRTUseCase = mock()

    private lateinit var viewmodel: GithubUserListViewModel

    @Before
    fun init() {
        viewmodel = GithubUserListViewModel(
            searchGithubUserProfileRTUseCase = searchGithubUserUseCase
        )
    }

    @Test
    fun `state is initially empty`() = runTest {
        Assert.assertEquals(UserListUiState.Empty, viewmodel.userListState.value)
    }

    @Test
    fun `state is successful when the search result is successful `() = runTest {

        whenever(searchGithubUserUseCase(any())).thenReturn(Resource.success(flowOf(emptyList())))

        viewmodel.searchUser("")

        Assert.assertTrue(viewmodel.userListState.value is UserListUiState.Success)
    }

    @Test
    fun `state is error when the search result fails `() = runTest {

        whenever(searchGithubUserUseCase(any())).thenReturn(Resource.error(""))

        viewmodel.searchUser("")

        Assert.assertEquals(
            UserListUiState.Error(""),
            viewmodel.userListState.value,
        )
    }
}