package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class JamuResponse(

	@field:SerializedName("data")
	val data: List<JamuItem>,
)

data class AddJamuResponse(

	@field:SerializedName("data")
	val data: JamuItem,

	@field:SerializedName("message")
	val message: String
)

data class UpdateJamuResponse(
	@field:SerializedName("data")
	val data: JamuItem,
)

data class DeleteJamuResponse(
	@field:SerializedName("name")
	val name: String
)


data class JamuItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("ingredients")
	val ingredients: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("source")
	val source: String,

	@field:SerializedName("steps")
	val steps: String,

	@field:SerializedName("main_ingredient")
	val mainIngredient: String
)