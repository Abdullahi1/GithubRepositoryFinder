package com.example.repositories.screen

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
import com.example.commondesign.component.LoadingWheel
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.domain.model.GithubRepositoryData
import com.example.repositories.component.CenteredImage
import com.example.repositories.component.GithubRepositoryItemCard
import com.example.repositories.route.RepositoryScreenRoute

@Composable
fun GithubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepositoryListViewModel = hiltViewModel(),
) {

    val repositoryUiState by viewModel.repositoryState.collectAsStateWithLifecycle()

    GithubRepositoryListScreenContent(
        modifier = modifier,
        uiState = repositoryUiState,
        onSearchClicked = viewModel::searchRepository
    )
}

@Composable
fun GithubRepositoryListScreenContent(
    modifier: Modifier = Modifier,
    uiState: RepositoryUiState = RepositoryUiState.Empty,
    onSearchClicked: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Repositories", fontSize = 18.sp, fontWeight = FontWeight.W700)
        Spacer(modifier = Modifier.height(10.dp))
        CustomSearchBar(
            hintText = "Search for repositories...", onSearchClicked = {
                if (it.text.isNotEmpty()) {
                    onSearchClicked(it.text)
                }
            }, enabled = uiState !is RepositoryUiState.Loading
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = if (uiState is RepositoryUiState.Success && !uiState.isEmpty()) Arrangement.Top else Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            when (uiState) {
                RepositoryUiState.Empty -> CenteredImage(text = "Search Github for repositories, issues and pull requests!")
                is RepositoryUiState.Error -> CenteredImage(text = uiState.message)
                RepositoryUiState.Loading -> LoadingWheel("Loading")
                is RepositoryUiState.Success -> {
                    if (uiState.isEmpty()) {
                        CenteredImage(text = "We’ve searched the ends of the earth, repository not found, please try again")
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(count = uiState.repositories.size, key = { uiState.repositories[it].repositoryId }) { index ->
                                GithubRepositoryItemCard(
                                    modifier = Modifier.animateItem().padding(10.dp),
                                    repositoryData = uiState.repositories[index]
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
private fun GithubRepositoryListScreenPreview() {
    GithubRepositoryFinderTheme {
        GithubRepositoryListScreenContent(
            onSearchClicked = {}, uiState = RepositoryUiState.Success(
                repositories = listOf(
                    GithubRepositoryData(
                        repositoryId = 100,
                        fullName = "Vundere/Python",
                        githubUrl = "",
                        topics = listOf(
                            "Design system",
                            "Component- misc",
                            "Status- New",
                            "Html",
                            "Css",
                            "JS",
                            "Html",
                            "Css",
                            "JS"
                        ),
                        language = "Python",
                        repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
                        starGazersCount = 10,
                        userUrl = "",
                        userImageUrl = ""
                    ), GithubRepositoryData(
                        repositoryId = 101,
                        fullName = "Vundere/Python",
                        githubUrl = "",
                        topics = listOf(
                            "Design system",
                            "Component- misc",
                            "Status- New",
                            "Html",
                            "Css",
                            "JS",
                            "Html",
                            "Css",
                            "JS"
                        ),
                        language = "Python",
                        repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
                        starGazersCount = 10,
                        userUrl = "",
                        userImageUrl = ""
                    ), GithubRepositoryData(
                        repositoryId = 102,
                        fullName = "Vundere/Python",
                        githubUrl = "",
                        topics = listOf(
                            "Design system",
                            "Component- misc",
                            "Status- New",
                            "Html",
                            "Css",
                            "JS",
                            "Html",
                            "Css",
                            "JS"
                        ),
                        language = "Python",
                        repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
                        starGazersCount = 10,
                        userUrl = "",
                        userImageUrl = ""
                    ), GithubRepositoryData(
                        repositoryId = 103,
                        fullName = "Vundere/Python",
                        githubUrl = "",
                        topics = listOf(
                            "Design system",
                            "Component- misc",
                            "Status- New",
                            "Html",
                            "Css",
                            "JS",
                            "Html",
                            "Css",
                            "JS"
                        ),
                        language = "Python",
                        repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
                        starGazersCount = 10,
                        userUrl = "",
                        userImageUrl = ""
                    ), GithubRepositoryData(
                        repositoryId = 104,
                        fullName = "Vundere/Python",
                        githubUrl = "",
                        topics = listOf(
                            "Design system",
                            "Component- misc",
                            "Status- New",
                            "Html",
                            "Css",
                            "JS",
                            "Html",
                            "Css",
                            "JS"
                        ),
                        language = "Python",
                        repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
                        starGazersCount = 10,
                        userUrl = "",
                        userImageUrl = ""
                    )
                )
            )
        )
    }
}

fun NavGraphBuilder.repositoryList(
    modifier: Modifier = Modifier,
) {
    composable<RepositoryScreenRoute> {
        GithubRepositoryListScreen(
            modifier = modifier
        )
    }
}