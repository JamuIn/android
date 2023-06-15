package com.adiluhung.jamuin.data.sources

import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.model.ProfileItemModel
import com.adiluhung.jamuin.route.Routes

object ProfileItem {
    val data = listOf(
        ProfileItemModel(
            Id = 2,
            Icon = R.drawable.love,
            Label = "Favorit",
            Route = Routes.Favorite.route
        ),
        ProfileItemModel(
            Id = 3,
            Icon = R.drawable.history,
            Label = "Riwayat Pesanan",
            Route = "/"
        )
    )
}