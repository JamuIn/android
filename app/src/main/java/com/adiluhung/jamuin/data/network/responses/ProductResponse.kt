package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class ProductResponse(

    @field:SerializedName("products")
    val products: List<ProductsItem>
)

data class ProductsItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("jamu_category_id")
    val jamuCategoryId: Int,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("seller")
    val seller: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("stock")
    val stock: Int,

    @field:SerializedName("main_ingredient")
    val mainIngredient: String
)

data class DetailProductResponse(

    @field:SerializedName("product")
    val product: ProductsItem
)