package com.example.userdetails.screen

import com.example.domain.common.Resource
import com.example.domain.user.GetGithubUserProfileUseCase
import com.example.domain.user.GetGithubUserRepositoryUseCase
import com.example.userdetails.common.FakeData
import com.example.userdetails.common.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever


class GithubUserDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getGithubUserProfileUseCase: GetGithubUserProfileUseCase = mock()
    private val getGithubUserRepositoryUseCase: GetGithubUserRepositoryUseCase = mock()

    private lateinit var viewmodel: GithubUserDetailViewModel

    @Before
    fun init() {
        viewmodel = GithubUserDetailViewModel(
            getGithubUserRepositoryUseCase = getGithubUserRepositoryUseCase,
            getGithubUserProfileUseCase = getGithubUserProfileUseCase
        )
    }

    @Test
    fun `state is initially empty`() = runTest {
        Assert.assertEquals(UserDetailsUiState.Empty, viewmodel.userDetailsState.value)
    }

    @Test
    fun `state is successful when getGithubUserProfileUseCase and getGithubUserRepositoryUseCase are successful `() =
        runTest {

            whenever(getGithubUserProfileUseCase(any())).thenReturn(
                Resource.success(
                    data = FakeData.userProfile
                )
            )

            whenever(getGithubUserRepositoryUseCase(any())).thenReturn(
                Resource.success(emptyList())
            )

            viewmodel.getUserProfile("")

            Assert.assertEquals(
                UserDetailsUiState.Success(user = FakeData.userProfile, repositories = emptyList()),
                viewmodel.userDetailsState.value,
            )
        }

    @Test
    fun `state is successful when getGithubUserProfileUseCase is successful and getGithubUserRepositoryUseCase fails `() =
        runTest {

            whenever(getGithubUserProfileUseCase(any())).thenReturn(
                Resource.success(
                    data = FakeData.userProfile
                )
            )

            whenever(getGithubUserRepositoryUseCase(any())).thenReturn(
                Resource.error("")
            )

            viewmodel.getUserProfile("")

            Assert.assertEquals(
                UserDetailsUiState.Success(user = FakeData.userProfile, repositories = null),
                viewmodel.userDetailsState.value,
            )
        }

    @Test
    fun `state is error when the getGithubUserProfileUseCase fails `() = runTest {

        whenever(getGithubUserProfileUseCase(any())).thenReturn(
            Resource.error("")
        )

        viewmodel.getUserProfile("")

        Assert.assertEquals(
            UserDetailsUiState.Error(""),
            viewmodel.userDetailsState.value,
        )
    }
}