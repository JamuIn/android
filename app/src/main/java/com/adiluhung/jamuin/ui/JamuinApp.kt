package com.adiluhung.jamuin.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.screen.customer.cart.CartScreen
import com.adiluhung.jamuin.ui.screen.customer.home.HomeScreen
import com.adiluhung.jamuin.ui.screen.customer.profile.ProfileScreen
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.screen.auth.LoginScreen
import com.adiluhung.jamuin.ui.screen.auth.RegisterScreen
import com.adiluhung.jamuin.ui.screen.auth.RoleScreen
import com.adiluhung.jamuin.ui.screen.customer.checkout.CheckoutScreen
import com.adiluhung.jamuin.ui.screen.customer.detailArticle.DetailArticleScreen
import com.adiluhung.jamuin.ui.screen.customer.detailProduct.DetailProductScreen
import com.adiluhung.jamuin.ui.screen.customer.editAddress.EditAddressScreen
import com.adiluhung.jamuin.ui.screen.customer.editProfile.EditProfileScreen
import com.adiluhung.jamuin.ui.screen.customer.favorite.FavoriteScreen
import com.adiluhung.jamuin.ui.screen.seller.home.SellerHomeScreen
import com.adiluhung.jamuin.ui.screen.seller.order.SellerOrderScreen
import com.adiluhung.jamuin.ui.screen.seller.profile.SellerProfileScreen
import com.adiluhung.jamuin.ui.screen.seller.shop.SellerShopScreen


@Composable
fun JamuinApp(
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(context = LocalContext.current)
    )
) {
    val navController = rememberNavController()

    val token = viewModel.getLoggedInUser().observeAsState().value
    Log.d("JamuinApp", "Token: $token")

    val startDestination = if (token == null) {
        Routes.Login.routes
    } else {
        Routes.Home.routes
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
        composable(Routes.Home.routes) {
            HomeScreen(navController)
        }
        composable(Routes.Photo.routes) {
            // HomeScreen(navController)
        }
        composable(Routes.Cart.routes) {
            CartScreen(navController)
        }
        composable(Routes.Profile.routes) {
            ProfileScreen(navController)
        }
        composable(Routes.EditProfile.routes) {
            EditProfileScreen(navController)
        }
        composable(Routes.DetailArticle.routes) {
            DetailArticleScreen(navController, it.arguments?.getString("id")?.toInt() ?: 0)
        }
        composable(Routes.DetailProduct.routes) {
            DetailProductScreen(navController, it.arguments?.getString("id")?.toInt() ?: 0)
        }
        composable(Routes.Favorite.routes) {
            FavoriteScreen(navController)
        }
        composable(Routes.Checkout.routes){
            CheckoutScreen(navController = navController)
        }
        composable(Routes.EditAddress.routes){
            EditAddressScreen(navController = navController)
        }

        // Seller
        composable(Routes.SellerHome.routes) {
            SellerHomeScreen(navController = navController)
        }
        composable(Routes.SellerShop.routes) {
            SellerShopScreen(navController = navController)
        }
        composable(Routes.SellerOrder.routes) {
            SellerOrderScreen(navController = navController)
        }
        composable(Routes.SellerProfile.routes) {
            SellerProfileScreen(navController = navController)
        }
    }

}