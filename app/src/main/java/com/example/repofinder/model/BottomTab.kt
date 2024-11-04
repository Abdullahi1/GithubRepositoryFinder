package com.example.repofinder.model

import androidx.annotation.DrawableRes
import com.example.commondesign.navigation.Route
import com.example.home.route.HomeScreenRoute
import com.example.repofinder.R
import com.example.repositories.route.RepositoryScreenRoute
import com.example.users.route.UserScreenRoute


sealed class BottomTab(
    val route: Route,
    val label: String,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inActiveIcon: Int,
) {
    data object HomeBottomTab : BottomTab(
        route = HomeScreenRoute,
        label = "Home",
        activeIcon = R.drawable.ic_active_home,
        inActiveIcon = R.drawable.ic_inactive_home
    )

    data object RepositoryBottomTab : BottomTab(
        route = RepositoryScreenRoute,
        label = "Repository",
        activeIcon = R.drawable.ic_active_search,
        inActiveIcon = R.drawable.ic_inactive_search
    )

    data object UserBottomTab : BottomTab(
        route = UserScreenRoute,
        label = "Users",
        activeIcon = R.drawable.ic_active_user,
        inActiveIcon = R.drawable.ic_inactive_user
    )

    companion object {
        val bottomTabScreenTabs = listOf<BottomTab>(
            HomeBottomTab, RepositoryBottomTab, UserBottomTab
        )
    }
}
