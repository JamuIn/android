package com.adiluhung.jamuin.route

import com.adiluhung.jamuin.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Chat : NavigationItem(Routes.Chat.routes, R.drawable.chat, "Chat")
    object Home : NavigationItem(Routes.Home.routes, R.drawable.home, "Home")
    object Photo : NavigationItem(Routes.Photo.routes, R.drawable.idea, "Photo")
    object Cart : NavigationItem(Routes.Cart.routes, R.drawable.cart_shopping, "Cart")
    object Profile : NavigationItem(Routes.Profile.routes, R.drawable.profile, "Profile")
}
