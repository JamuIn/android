package com.adiluhung.jamuin.ui.screen.customer.payment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.CheckoutResponse
import com.adiluhung.jamuin.data.network.responses.CreateOrderResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewModel(private val pref: UserPreferences) : ViewModel() {
    private val token = runBlocking {
        pref.getUserToken().map { token -> token }.first()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun updateOrder(orderId: Int, totalPrice:Int, status: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().updateOrder("Bearer $token", orderId, totalPrice, status)
        client.enqueue(object :Callback<CreateOrderResponse>{
            override fun onResponse(
                call: Call<CreateOrderResponse>,
                response: Response<CreateOrderResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("PaymentViewModel", "onResponse: ${responseBody.toString()}")
                }
            }

            override fun onFailure(call: Call<CreateOrderResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("PaymentViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun checkoutOrder(paymentAddress: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().checkoutOrder("Bearer $token", paymentAddress)
        client.enqueue(object: Callback<CheckoutResponse>{
            override fun onResponse(
                call: Call<CheckoutResponse>,
                response: Response<CheckoutResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("PaymentViewModel", "onResponse: ${responseBody.toString()}")
                }
            }

            override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("PaymentViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}