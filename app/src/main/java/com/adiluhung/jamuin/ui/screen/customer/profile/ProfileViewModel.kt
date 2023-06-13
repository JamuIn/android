package com.adiluhung.jamuin.ui.screen.customer.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.LogoutResponse
import com.adiluhung.jamuin.data.network.responses.UserResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val pref: UserPreferences) : ViewModel() {

    private val token = runBlocking {
        pref.getUserToken()
            .map { token -> token }
            .first()
    }

    private val _user = MutableLiveData<UserResponse?>()
    val user: LiveData<UserResponse?> = _user

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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

    fun logout() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().logout("Bearer $token")
        client.enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    _message.value = responseBody?.message

                    viewModelScope.launch {
                        pref.deleteToken()
                    }
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}