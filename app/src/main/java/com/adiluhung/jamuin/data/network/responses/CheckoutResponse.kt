package com.adiluhung.jamuin.data.network.responses

data class CheckoutResponse(
	val totalPrice: Int,
	val productDetail: List<String>,
	val userAddress: String,
	val paymentAddress: String,
	val shippingCost: Int,
	val message: String,
	val cart: List<CartItem>
)

data class CartItem(
	val quantity: Int,
	val userId: Int,
	val price: Int,
	val productId: Int,
	val id: Int,
	val productDescription: String,
	val productName: String,
	val mainIngredient: String
)

