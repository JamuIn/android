package com.adiluhung.jamuin.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.screen.CartScreen
import com.adiluhung.jamuin.ui.screen.HomeScreen
import com.adiluhung.jamuin.ui.screen.ProfileScreen
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.screen.auth.LoginScreen
import com.adiluhung.jamuin.ui.screen.auth.RegisterScreen
import com.adiluhung.jamuin.ui.screen.auth.RoleScreen
import com.adiluhung.jamuin.ui.screen.seller.home.SellerHomeScreen
import com.adiluhung.jamuin.ui.screen.seller.order.SellerOrderScreen
import com.adiluhung.jamuin.ui.screen.seller.profile.SellerProfileScreen
import com.adiluhung.jamuin.ui.screen.seller.shop.SellerShopScreen


@Composable
fun JamuinApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(context = LocalContext.current)
    )
) {
    val navController = rememberNavController()

    val token = viewModel.getLoggedInUser().observeAsState().value

    val startDestination = if (token == null) {
        Routes.Login.routes
    } else {
        Routes.SellerHome.routes
    }

    NavHost(navController, startDestination = startDestination) {
        composable(Routes.Login.routes) {
            LoginScreen(navController)
        }
        composable(Routes.Register.routes) {
            RegisterScreen(navController)
        }
        composable(Routes.Role.routes) {
            RoleScreen(navController)
        }
        composable(Routes.Dashboard.routes) {
            HomeScreen(PaddingValues(16.dp), navController)
        }
        composable(Routes.Cart.routes) {
            CartScreen(navController)
        }
        composable(Routes.Profile.routes) {
            ProfileScreen(navController)
        }

        // Seller
        composable(Routes.SellerHome.routes){
            SellerHomeScreen(navController = navController)
        }
        composable(Routes.SellerShop.routes){
            SellerShopScreen(navController = navController)
        }
        composable(Routes.SellerOrder.routes){
            SellerOrderScreen(navController = navController)
        }
        composable(Routes.SellerProfile.routes){
            SellerProfileScreen(navController = navController)
        }
    }

}