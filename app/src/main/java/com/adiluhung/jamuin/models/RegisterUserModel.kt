package com.adiluhung.jamuin.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterUserModel(
    var fullname: String,
    var username: String,
    var email: String,
    var password: String,
    var passwordConfirmation: String,
    var address: String,
    var phoneNumber: String
): Parcelable

