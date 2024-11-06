package com.example.users.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.commondesign.component.CustomSearchBar
import com.example.commondesign.component.OverlayLoadingWheel
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.domain.model.GithubUserProfileData
import com.example.users.component.CenteredImage
import com.example.users.component.GithubUserItemCard
import com.example.users.route.UserScreenRoute

@Composable
fun GithubUserListScreen(
    modifier: Modifier = Modifier,
    onUserSelected: (GithubUserProfileData) -> Unit,
    viewModel: GithubUserListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.userListState.collectAsStateWithLifecycle()

    GithubUserListScreenContent(
        modifier = modifier,
        uiState = uiState,
        onSearchClicked = viewModel::searchUser,
        onUserSelected = onUserSelected
    )
}

@Composable
fun GithubUserListScreenContent(
    modifier: Modifier = Modifier,
    uiState: UserListUiState = UserListUiState.Empty,
    onSearchClicked: (String) -> Unit,
    onUserSelected: (GithubUserProfileData) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Users", fontSize = 18.sp, fontWeight = FontWeight.W700)
        Spacer(modifier = Modifier.height(10.dp))
        CustomSearchBar(hintText = "Search for users...", onSearchClicked = {
            if (it.text.isNotEmpty()) {
                onSearchClicked(it.text)
            }
        }, enabled = uiState !is UserListUiState.Loading)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = if (uiState is UserListUiState.Success && !uiState.isEmpty()) Arrangement.Top else Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            when (uiState) {
                UserListUiState.Empty -> CenteredImage(text = "Search Github for users...")
                is UserListUiState.Error -> CenteredImage(text = uiState.message)
                UserListUiState.Loading -> OverlayLoadingWheel("Loading")
                is UserListUiState.Success -> {
                    if (uiState.isEmpty()) {
                        CenteredImage(text = "We’ve searched the ends of the earth and we’ve not found this user, please try again")
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(
                                count = uiState.users.size,
                                key = { uiState.users[it].userName }) { index ->
                                GithubUserItemCard(
                                    modifier = Modifier
                                        .animateItem()
                                        .padding(4.dp, 0.dp, 4.dp, 4.dp),
                                    userData = uiState.users[index],
                                    onClick = onUserSelected
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubUserListScreenPreview() {
    GithubRepositoryFinderTheme {
        GithubUserListScreenContent(onSearchClicked = {}, onUserSelected = {})
    }
}

fun NavGraphBuilder.usersList(
    modifier: Modifier = Modifier,
    onUserSelected: (GithubUserProfileData) -> Unit,
) {
    composable<UserScreenRoute> {
        GithubUserListScreen(
            modifier = modifier,
            onUserSelected = onUserSelected
        )
    }
}