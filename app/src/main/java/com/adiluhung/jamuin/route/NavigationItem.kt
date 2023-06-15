package com.adiluhung.jamuin.route

import com.adiluhung.jamuin.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Chat : NavigationItem(Routes.Chat.route, R.drawable.chat, "Chat")
    object Home : NavigationItem(Routes.Home.route, R.drawable.ic_home, "Home")
    object Photo : NavigationItem(Routes.Photo.route, R.drawable.ic_camera, "Photo")
    object Cart : NavigationItem(Routes.Cart.route, R.drawable.ic_cart, "Cart")
    object Profile : NavigationItem(Routes.Profile.route, R.drawable.ic_profile, "Profile")

    //object Favorite : NavigationItem(Routes.Favorite.routes, R.drawable.love, "favorite")

    // Seller
    object SellerHome : NavigationItem(Routes.SellerHome.route, R.drawable.ic_home, "Seller Home")
    object SellerShop : NavigationItem(Routes.SellerShop.route, R.drawable.ic_shop, "Seller Shop")
    object SellerOrder : NavigationItem(Routes.SellerOrder.route, R.drawable.ic_order, "Seller Order")
    object SellerProfile : NavigationItem(Routes.SellerProfile.route, R.drawable.ic_profile, "Seller Profile")

}
