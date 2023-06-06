package com.adiluhung.jamuin.data.network.responses

data class AuthResponse(
    val role: List<String>?,
    val message: String,
    val user: User?,
    val token: String?,
    val errors: Errors?
)

data class Errors(
    val username: List<String>?,
    val email: List<String>?
)

data class User(
    val profileImg: String?,
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

