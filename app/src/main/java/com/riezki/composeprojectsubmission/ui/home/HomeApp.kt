package com.riezki.composeprojectsubmission.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.riezki.composeprojectsubmission.ui.about.AboutScreen
import com.riezki.composeprojectsubmission.ui.detail.DetailScreen
import com.riezki.composeprojectsubmission.ui.navigation.Screen

@Composable
fun HomeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Home.route) {
                MyTopBarApp {
                    navController.navigate(Screen.About.route)
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeContent(navigateToDetail = { detailId ->
                    navController.navigate(Screen.Detail.createRoute(detailId))
                })
            }

            composable(Screen.About.route) {
                AboutScreen()
            }

            composable(
                Screen.Detail.route,
                arguments = listOf(navArgument("detailId") { type = NavType.StringType }),
            ) {
                val id = it.arguments?.getString("detailId") ?: ""
                DetailScreen(
                    idDetail = id,
                    navigateBack = {
                        navController.navigateUp()
                    })
            }
        }
    }
}