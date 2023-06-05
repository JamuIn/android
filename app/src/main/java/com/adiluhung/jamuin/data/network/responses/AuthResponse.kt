package com.adiluhung.jamuin.data.network.responses

data class AuthResponse(
    val role: String,
    val message: String,
    val user: User,
    val token: String
)

data class User(
    val profileImg: String? = null,
    val fullName: String,
    val address: String,
    val phoneNumber: String,
    val id: Int,
    val email: String,
    val username: String
)

data class LogoutResponse(
    val message: String
)

