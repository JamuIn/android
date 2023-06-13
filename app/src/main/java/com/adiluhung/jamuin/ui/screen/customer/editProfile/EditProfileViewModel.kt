package com.adiluhung.jamuin.ui.screen.customer.editProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.UserResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileViewModel(private val pref: UserPreferences) : ViewModel() {

    private val token = runBlocking {
        pref.getUserToken()
            .map { token -> token }
            .first()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _user = MutableLiveData<UserResponse?>()
    val user: LiveData<UserResponse?> = _user

    init {
        getUserByToken()
    }

    private fun getUserByToken() {
        _isLoading.value = true
        if (token != null) {
            val client = ApiConfig.getApiService().getUserByToken("Bearer $token")
            client.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val responseBody = response.body()
                        _user.value = responseBody
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
                }
            })
        }
    }

    fun updateProfile(
        id: Int,
        fullName: String,
        username: String,
        email: String,
        address: String,
        phoneNumber: String,
        image: String?,
    ) {
        _isLoading.value = true
        if (token != null) {
            val client = ApiConfig.getApiService().updateUser(
                "Bearer $token",
                id,
                fullName,
                username,
                email,
                phoneNumber,
                address,
                image
            )
            client.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val responseBody = response.body()
                        _user.value = responseBody
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
                }
            })
        }
    }
}