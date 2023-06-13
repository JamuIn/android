package com.adiluhung.jamuin.route

sealed class Routes(val routes: String) {
    object Register : Routes("register")
    object Role : Routes("role")
    object Login : Routes("login")
    object Chat : Routes("chat")
    object Home : Routes("home")
    object Photo : Routes("photo")
    object Cart : Routes("cart")
    object Profile : Routes("profile")
    object Search : Routes("search")
    object Checkout : Routes("checkout")
    object EditProfile : Routes("edit_profile")
    object EditAddress : Routes("edit_address")
    object DetailArticle : Routes("detail_article/{id}") {
        fun createRoute(id: Int) = "detail_article/$id"
    }

    object DetailProduct : Routes("detail_product/{id}") {
        fun createRoute(id: Int) = "detail_product/$id"
    }
    object Favorite : Routes("favorite")

    //    object Detail : Routes("detail/{id}") {
    //        fun createRoute(id: Int) = "detail/$id"
    //    }

    // Seller
    object SellerHome : Routes("seller_home")
    object SellerShop : Routes("seller_shop")
    object SellerOrder : Routes("seller_order")
    object SellerProfile : Routes("seller_profile")
}
