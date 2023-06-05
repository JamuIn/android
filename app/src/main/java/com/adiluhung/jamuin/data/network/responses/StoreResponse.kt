package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @field:SerializedName("stores")
    val stores: List<StoreItem?>? = null,
)

data class DetailStoreResponse(
    @field:SerializedName("store")
    val store: StoreItem
)

data class AddStoreResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("store")
    val store: StoreItem
)

data class StoreItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int
)
