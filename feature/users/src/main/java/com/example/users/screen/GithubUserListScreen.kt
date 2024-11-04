package com.example.users.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.commondesign.component.CustomSearchBar
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.users.route.UserScreenRoute

@Composable
fun GithubUserListScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Users", fontSize = 18.sp, fontWeight = FontWeight.W700)
        Spacer(modifier = Modifier.height(10.dp))
        CustomSearchBar(hintText = "Search for users...", onSearchClicked = {})
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Preview(showBackground = true)
@Composable
private fun GGithubUserListScreenPreview() {
    GithubRepositoryFinderTheme {
        GithubUserListScreen()
    }
}

fun NavGraphBuilder.usersList(
    modifier: Modifier = Modifier,
) {
    composable<UserScreenRoute> {
        GithubUserListScreen(
            modifier = modifier
        )
    }
}