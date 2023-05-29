package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName


data class UserResponses(
    @field:SerializedName("id_user")
    val idUser: Long,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("phone_number")
    val phoneNumber: String,

    @field:SerializedName("profile_picture")
    val profilePicture: String,
)