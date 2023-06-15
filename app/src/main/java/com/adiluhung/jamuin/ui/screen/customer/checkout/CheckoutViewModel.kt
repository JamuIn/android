package com.adiluhung.jamuin.ui.screen.customer.checkout

import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.CartResponse
import com.adiluhung.jamuin.data.network.responses.CartsItem
import com.adiluhung.jamuin.data.network.responses.CheckoutResponse
import com.adiluhung.jamuin.data.network.responses.CreateOrderResponse
import com.adiluhung.jamuin.data.network.responses.UserResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutViewModel(private val pref: UserPreferences) : ViewModel() {
    private val token = runBlocking {
        pref.getUserToken().map { token -> token }.first()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _user = MutableLiveData<UserResponse?>()
    val user: MutableLiveData<UserResponse?> = _user

    private val _listCartItem = MutableLiveData<List<CartsItem>>()
    val listCartItem: MutableLiveData<List<CartsItem>> = _listCartItem

    private val _message = MutableLiveData<String>()
    val message: MutableLiveData<String> = _message

    private val _orderId = MutableLiveData<Int>()
    val orderId: LiveData<Int> = _orderId

    init {
        getItemInUserCart()
        getUserByToken()
    }

    private fun getUserByToken() {
        _isLoading.value = true
        if (token != null) {
            val client = ApiConfig.getApiService().getUserByToken("Bearer $token")
            client.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>, response: Response<UserResponse>
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

    private fun getItemInUserCart() {
        _isLoading.value = true
        if (token != null) {
            val client = ApiConfig.getApiService().getUserCart("Bearer $token")
            client.enqueue(object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>, response: Response<CartResponse>
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

    fun createOrder(totalPrice: Int, status: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().createOrder("Bearer $token", totalPrice, status)
        client.enqueue(object : Callback<CreateOrderResponse> {
            override fun onResponse(
                call: Call<CreateOrderResponse>,
                response: Response<CreateOrderResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _message.value = "Lanjutkan ke pembayaran"
                    _orderId.value = response.body()?.order?.id
                }
            }

            override fun onFailure(call: Call<CreateOrderResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("CheckoutViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}