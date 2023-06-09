package com.adiluhung.jamuin.route

sealed class Routes(val routes: String) {
    object Register : Routes("register")
    object Role : Routes("role")
    object Login : Routes("login")
    object Dashboard : Routes("dashboard")
    object Chat : Routes("chat")
    object Home : Routes("home")
    object Photo : Routes("photo")
    object Cart : Routes("cart")
    object Profile : Routes("profile")
    object Search : Routes("search")
    object Checkout : Routes ("checkout")
    object AddressEdit : Routes ("address edit")
    object DetailArticle : Routes("detail article/{id}") {
        fun createRoute(id: Int) = "detail article/$id"
    }
//    object Favorite : Routes("favorite")
//    object Detail : Routes("detail/{id}") {
//        fun createRoute(id: Int) = "detail/$id"
//    }
}
