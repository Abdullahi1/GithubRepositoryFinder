package com.example.repofinder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.repofinder.model.BottomTab
import com.example.repofinder.navigation.AppNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarList = BottomTab.bottomTabScreenTabs

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember { derivedStateOf { navBackStackEntry?.destination?.route } }


    Scaffold(modifier = Modifier.fillMaxSize(), content = { innerPadding ->
        AppNavHost(modifier = Modifier.padding(innerPadding), navController = navController)
    }, bottomBar = {
        NavigationBar {
            bottomBarList.forEach { bottomTab ->
                NavigationBarItem(colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                    modifier = Modifier.fillMaxWidth(),
                    selected = (bottomTab.route::class.qualifiedName == currentRoute),
                    onClick = {
                        val topLevelNavOptions = navOptions {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                        navController.navigate(
                            route = bottomTab.route, navOptions = topLevelNavOptions
                        )
                    },
                    label = {
                        Text(
                            text = bottomTab.label, fontWeight = FontWeight.W400, fontSize = 10.sp
                        )
                    },
                    icon = {
                        Image(
                            painter = painterResource(
                                id = if (bottomTab.route::class.qualifiedName == currentRoute) bottomTab.activeIcon
                                else bottomTab.inActiveIcon
                            ),
                            contentDescription = bottomTab.label,
                            contentScale = ContentScale.FillHeight
                        )
                    })
            }
        }
    })
}