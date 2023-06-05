package com.adiluhung.jamuin.data.sources

import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.model.ProfileItemModel

object ProfileItem {
    val data = listOf<ProfileItemModel>(
        ProfileItemModel(
            Id = 1,
            Icon = R.drawable.home,
            Label = "Alamat",
            Route = "/"
        ),
        ProfileItemModel(
            Id = 2,
            Icon = R.drawable.love,
            Label = "Favorit",
            Route = "/"
        ),
        ProfileItemModel(
            Id = 3,
            Icon = R.drawable.history,
            Label = "Riwayat Pesanan",
            Route = "/"
        )
    )
}