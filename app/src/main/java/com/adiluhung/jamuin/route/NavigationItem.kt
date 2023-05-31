package com.adiluhung.jamuin.route

import com.adiluhung.jamuin.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Chat : NavigationItem(Routes.Chat.routes, R.drawable.ic_baseline_chat_24, "Chat")
}
