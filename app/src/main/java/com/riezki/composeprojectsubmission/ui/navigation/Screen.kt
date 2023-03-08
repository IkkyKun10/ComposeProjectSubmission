package com.riezki.composeprojectsubmission.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Detail: Screen("home/{detailId}") {
        fun createRoute(detailId: String) = "home/$detailId"
    }
    object About: Screen("about")
}