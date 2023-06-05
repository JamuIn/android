package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class IngredientResponse(
	@field:SerializedName("data")
	val data: List<IngredientItem?>? = null,
)

data class DetailIngredientResponse(
	@field:SerializedName("data")
	val data: IngredientItem,
)

data class IngredientItem(
	val image: String,
	val updatedAt: String,
	val name: String,
	val createdAt: String,
	val id: Int
)

