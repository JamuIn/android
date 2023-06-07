package com.adiluhung.jamuin.ui.screen.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.LoginResponse
import com.adiluhung.jamuin.data.network.responses.RegisterResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import com.adiluhung.jamuin.ui.common.UiState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(private val pref: UserPreferences) : ViewModel() {
    private val _uiState = MutableLiveData<UiState<String>>(UiState.Empty)
    val uiState: LiveData<UiState<String>>
        get() = _uiState

    fun login(email: String, password: String) {
        _uiState.value = UiState.Loading
        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _uiState.value = UiState.Success(data.message)

                        viewModelScope.launch {
                            if(data.token != null) {
                                pref.saveUserToken(data.token)
                            }
                        }
                    } else {
                        _uiState.value = UiState.Error("Data tidak ditemukan")
                    }
                } else {
                    _uiState.value = UiState.Error("Email atau password salah")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _uiState.value = UiState.Error(t.message ?: "Unknown error")
            }
        })
    }

    fun register(
        fullname: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String,
        passwordConfirmation: String,
        address: String,
        role: String,
    ) {
        _uiState.value = UiState.Loading
        val client = ApiConfig.getApiService().register(
            fullname,
            username,
            email,
            phoneNumber,
            password,
            passwordConfirmation,
            address,
            role
        )
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _uiState.value = UiState.Success(data.message)

                        viewModelScope.launch {
                            if(data.token != null) {
                                pref.saveUserToken(data.token)
                            }
                        }
                    } else {
                        _uiState.value = UiState.Error("Data is null")
                    }
                } else {
                    _uiState.value = UiState.Error("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _uiState.value = UiState.Error(t.message ?: "Unknown error")
            }
        })
    }


}