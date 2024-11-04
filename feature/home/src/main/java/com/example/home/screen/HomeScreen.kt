package com.example.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.home.component.HomeMenuCardItem
import com.example.home.component.HomeMenuItem

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Home",
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier.padding(10.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(HomeMenuItem.homeMenuList.size) { index ->
                HomeMenuCardItem(
                    modifier = Modifier.padding(8.dp),
                    menuItem = HomeMenuItem.homeMenuList[index],
                    onClick = {},
                )
            }
        })
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    GithubRepositoryFinderTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen(modifier = Modifier.padding(8.dp))
        }
    }
}