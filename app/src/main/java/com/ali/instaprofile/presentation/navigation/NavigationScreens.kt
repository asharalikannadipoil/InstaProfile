package com.ali.instaprofile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ali.instaprofile.presentation.profile.ProfileScreen

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Profile.route,
        modifier = modifier
    ) {
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
