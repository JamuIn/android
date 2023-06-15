package com.adiluhung.jamuin.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.screen.auth.LoginScreen
import com.adiluhung.jamuin.ui.screen.auth.RegisterScreen
import com.adiluhung.jamuin.ui.screen.auth.RoleScreen
import com.adiluhung.jamuin.ui.screen.customer.cart.CartScreen
import com.adiluhung.jamuin.ui.screen.customer.checkout.CheckoutScreen
import com.adiluhung.jamuin.ui.screen.customer.detailArticle.DetailArticleScreen
import com.adiluhung.jamuin.ui.screen.customer.detailProduct.DetailProductScreen
import com.adiluhung.jamuin.ui.screen.customer.editAddress.EditAddressScreen
import com.adiluhung.jamuin.ui.screen.customer.editProfile.EditProfileScreen
import com.adiluhung.jamuin.ui.screen.customer.favorite.FavoriteScreen
import com.adiluhung.jamuin.ui.screen.customer.home.HomeScreen
import com.adiluhung.jamuin.ui.screen.customer.payment.PaymentScreen
import com.adiluhung.jamuin.ui.screen.customer.profile.ProfileScreen
import com.adiluhung.jamuin.ui.screen.customer.scanResult.ScanResultScreen
import com.adiluhung.jamuin.ui.screen.seller.home.SellerHomeScreen
import com.adiluhung.jamuin.ui.screen.seller.order.SellerOrderScreen
import com.adiluhung.jamuin.ui.screen.seller.profile.SellerProfileScreen
import com.adiluhung.jamuin.ui.screen.seller.shop.SellerShopScreen


@Composable
fun JamuinApp(
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(context = LocalContext.current)
    ),
    argument: String? = null
) {
    val navController = rememberNavController()

    val token = viewModel.getLoggedInUser().observeAsState().value

    var startDestination = if (token == null) {
        Routes.Login.route
    } else {
        if (argument != null) {
            Routes.ScanResult.route
        } else {
            Routes.Home.route
        }
    }

    NavHost(navController, startDestination = startDestination) {

        composable(Routes.Login.route) {
            LoginScreen(navController)
        }
        composable(Routes.Register.route) {
            RegisterScreen(navController)
        }
        composable(Routes.Role.route) {
            RoleScreen(navController)
        }
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(Routes.Photo.route) {
            // HomeScreen(navController)
        }
        composable(Routes.Cart.route) {
            CartScreen(navController)
        }
        composable(Routes.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Routes.EditProfile.route) {
            EditProfileScreen(navController)
        }
        composable(Routes.DetailArticle.route) {
            DetailArticleScreen(navController, it.arguments?.getString("id")?.toInt() ?: 0)
        }
        composable(Routes.DetailProduct.route) {
            DetailProductScreen(navController, it.arguments?.getString("id")?.toInt() ?: 0)
        }
        composable(Routes.Favorite.route) {
            FavoriteScreen(navController)
        }
        composable(Routes.Checkout.route) {
            CheckoutScreen(navController = navController)
        }
        composable(Routes.EditAddress.route) {
            EditAddressScreen(navController = navController)
        }
        composable(Routes.ScanResult.route) {
            ScanResultScreen(navController = navController, scanResult = argument)
        }
        composable(Routes.Payment.route) {
            PaymentScreen(
                navController = navController,
                orderId = it.arguments?.getString("orderId")?.toInt() ?: 0,
                paymentMethodId = it.arguments?.getString("paymentMethodId")?.toInt() ?: 0,
                totalPrice = it.arguments?.getString("totalPrice")?.toInt() ?: 0
            )
        }

        // Seller
        composable(Routes.SellerHome.route) {
            SellerHomeScreen(navController = navController)
        }
        composable(Routes.SellerShop.route) {
            SellerShopScreen(navController = navController)
        }
        composable(Routes.SellerOrder.route) {
            SellerOrderScreen(navController = navController)
        }
        composable(Routes.SellerProfile.route) {
            SellerProfileScreen(navController = navController)
        }
    }

}