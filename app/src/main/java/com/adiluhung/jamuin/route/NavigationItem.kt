package com.adiluhung.jamuin.route

import com.adiluhung.jamuin.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Chat : NavigationItem(Routes.Chat.routes, R.drawable.chat, "Chat")
    object Home : NavigationItem(Routes.Home.routes, R.drawable.ic_home, "Home")
    object Photo : NavigationItem(Routes.Photo.routes, R.drawable.ic_camera, "Photo")
    object Cart : NavigationItem(Routes.Cart.routes, R.drawable.ic_cart, "Cart")
    object Profile : NavigationItem(Routes.Profile.routes, R.drawable.ic_profile, "Profile")

    //object Favorite : NavigationItem(Routes.Favorite.routes, R.drawable.love, "favorite")

    // Seller
    object SellerHome : NavigationItem(Routes.SellerHome.routes, R.drawable.ic_home, "Seller Home")
    object SellerShop : NavigationItem(Routes.SellerShop.routes, R.drawable.ic_shop, "Seller Shop")
    object SellerOrder : NavigationItem(Routes.SellerOrder.routes, R.drawable.ic_order, "Seller Order")
    object SellerProfile : NavigationItem(Routes.SellerProfile.routes, R.drawable.ic_profile, "Seller Profile")

}
