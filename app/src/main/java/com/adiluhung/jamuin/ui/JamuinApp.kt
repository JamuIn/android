package com.adiluhung.jamuin.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.screen.auth.LoginScreen
import com.adiluhung.jamuin.ui.screen.auth.RegisterScreen


@Composable
fun JamuinApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Login.routes) {
        composable(Routes.Login.routes) {
            LoginScreen(navController)
        }
        composable(Routes.Register.routes) {
            RegisterScreen(navController)
        }
    }

}