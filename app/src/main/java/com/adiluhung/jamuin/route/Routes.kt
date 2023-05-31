package com.adiluhung.jamuin.route

sealed class Routes(val routes: String) {
    object Register : Routes("register")
    object Login : Routes("login")
    object Chat : Routes("chat")
}
