package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class CartResponse(

	@field:SerializedName("carts")
	val carts: List<CartsItem>
)

data class CartsItem(

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("product_id")
	val productId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("product_description")
	val productDescription: String,

	@field:SerializedName("product_name")
	val productName: String,

	@field:SerializedName("main_ingredient")
	val mainIngredient: String,

	@field:SerializedName("product_image")
	val productImage: String
)

data class AddCartResponse(
	@SerializedName("product_id")
	val productId: Int,
	@SerializedName("quantity")
	val quantity: Int
)

data class DeleteCartResponse(
	@SerializedName("message")
	val message: String
)
