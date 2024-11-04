package com.example.repofinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.home.route.HomeScreenRoute
import com.example.home.screen.homeScreen
import com.example.repositories.screen.repositoryList
import com.example.userdetails.screen.userDetails
import com.example.users.screen.usersList

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = HomeScreenRoute
    ) {
        homeScreen(modifier = modifier, onMenuClicked = {})
        repositoryList(modifier = modifier)
        usersList(modifier = modifier)
        userDetails(modifier = modifier, onBackPressed = {
            navController.navigateUp()
        })
    }

}