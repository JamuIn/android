package com.adiluhung.jamuin.models

data class OrderProduct(
    val name: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val image: String,
)