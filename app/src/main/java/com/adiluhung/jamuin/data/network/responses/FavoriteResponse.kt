package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class FavoriteResponse(

	@field:SerializedName("favorites")
	val favorites: List<FavoritesItem>
)

data class FavoritesItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("ingredients")
	val ingredients: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("source")
	val source: String,

	@field:SerializedName("steps")
	val steps: String
)

data class AddFavoriteResponse(
	@field:SerializedName("message")
	val message: String,
)
