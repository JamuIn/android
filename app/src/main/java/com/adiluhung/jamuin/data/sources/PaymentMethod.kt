package com.adiluhung.jamuin.data.sources

import com.adiluhung.jamuin.R

data class PaymentMethodModel(
    val id : Int,
    val title: String,
    val accoutNumber: String,
    val imageResId: Int
)

object PaymentMethod {
    val data = listOf<PaymentMethodModel>(
        PaymentMethodModel(
            id = 1,
            title = "BCA",
            accoutNumber = "1234567890",
            imageResId = R.drawable.bca
        ),
        PaymentMethodModel(
            id = 2,
            title = "BNI",
            accoutNumber = "1234567890",
            imageResId = R.drawable.bni
        ),
        PaymentMethodModel(
            id = 3,
            title = "BRI",
            accoutNumber = "1234567890",
            imageResId = R.drawable.bri
        ),
        PaymentMethodModel(
            id = 4,
            title = "Gopay",
            accoutNumber = "1234567890",
            imageResId = R.drawable.gopay
        ),
    )
}