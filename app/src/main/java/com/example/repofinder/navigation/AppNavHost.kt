package com.example.repofinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.home.route.HomeScreenRoute
import com.example.home.screen.homeScreen
import com.example.repositories.route.RepositoryScreenRoute
import com.example.repositories.screen.repositoryList
import com.example.userdetails.route.UserDetailScreenRoute
import com.example.userdetails.screen.userDetails
import com.example.users.route.UserScreenRoute
import com.example.users.screen.usersList

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController,) {
//    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = HomeScreenRoute
    ) {
        homeScreen(modifier = modifier, onMenuClicked = {
            if (it.id == 100){
                navController.navigate(UserScreenRoute)
            }else if (it.id == 101){
                navController.navigate(RepositoryScreenRoute)
            }
        })
        repositoryList()
        usersList(onUserSelected = {
            navController.navigate(route = UserDetailScreenRoute(userName = it.userName))
        })
        userDetails(modifier = modifier, onBackPressed = {
            navController.navigateUp()
        })
    }

}