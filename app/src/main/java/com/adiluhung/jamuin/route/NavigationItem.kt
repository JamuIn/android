package com.adiluhung.jamuin.route

import com.adiluhung.jamuin.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Chat : NavigationItem(Routes.Chat.routes, R.drawable.chat, "Chat")
    object Home : NavigationItem(Routes.Home.routes, R.drawable.home, "Home")
    object Photo : NavigationItem(Routes.Photo.routes, R.drawable.idea, "Photo")
    object Cart : NavigationItem(Routes.Cart.routes, R.drawable.cart_shopping, "Cart")
    object Profile : NavigationItem(Routes.Profile.routes, R.drawable.profile, "Profile")
    //object Favorite : NavigationItem(Routes.Favorite.routes, R.drawable.love, "favorite")

    // Seller
    object SellerHome : NavigationItem(Routes.SellerHome.routes, R.drawable.seller_home, "Seller Home")
    object SellerShop : NavigationItem(Routes.SellerShop.routes, R.drawable.seller_shop, "Seller Shop")
    object SellerOrder : NavigationItem(Routes.SellerOrder.routes, R.drawable.seller_order, "Seller Order")
    object SellerProfile : NavigationItem(Routes.SellerProfile.routes, R.drawable.seller_profile, "Seller Profile")

}
