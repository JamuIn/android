package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("profile_img")
	val profileImg: String?,

	@field:SerializedName("full_name")
	val fullName: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
