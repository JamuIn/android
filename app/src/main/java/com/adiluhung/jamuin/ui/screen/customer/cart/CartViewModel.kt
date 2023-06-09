package com.adiluhung.jamuin.ui.screen.customer.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.CartResponse
import com.adiluhung.jamuin.data.network.responses.CartsItem
import com.adiluhung.jamuin.data.network.responses.DeleteCartResponse
import com.adiluhung.jamuin.data.network.responses.UserResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(private val pref: UserPreferences) : ViewModel() {
    private val token = runBlocking {
        pref.getUserToken()
            .map { token -> token }
            .first()
    }

    private val _listCartItem = MutableLiveData<List<CartsItem>>()
    val listCartItem: LiveData<List<CartsItem>> = _listCartItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getItemInUserCart() {
        _isLoading.value = true
        if (token != null) {
            val client = ApiConfig.getApiService().getUserCart("Bearer $token")
            client.enqueue(object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val responseBody = response.body()
                        _listCartItem.value = responseBody?.carts
                    }
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
                }
            })
        }
    }

    fun deleteItemInCart(productId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().deleteUserCart("Bearer $token", productId)
        client.enqueue(object : Callback<DeleteCartResponse> {
            override fun onResponse(
                call: Call<DeleteCartResponse>,
                response: Response<DeleteCartResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    _message.value = responseBody?.message
                }
            }

            override fun onFailure(call: Call<DeleteCartResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}