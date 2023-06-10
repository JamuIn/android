package com.adiluhung.jamuin.ui.screen.customer.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.JamuItem
import com.adiluhung.jamuin.data.network.responses.JamuResponse
import com.adiluhung.jamuin.data.network.responses.ProductResponse
import com.adiluhung.jamuin.data.network.responses.ProductsItem
import com.adiluhung.jamuin.data.network.responses.UserResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val pref: UserPreferences) : ViewModel() {

    private val token = runBlocking {
        pref.getUserToken()
            .map { token -> token }
            .first()
    }

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _listRecipe = MutableLiveData<List<JamuItem>>()
    val listRecipe: LiveData<List<JamuItem>> = _listRecipe

    private val _listProduct = MutableLiveData<List<ProductsItem>>()
    val listProduct: LiveData<List<ProductsItem>> = _listProduct

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getUserByToken()
        getRecommendedJamu()
        getTopProduct()
    }

    private fun getTopProduct() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllProduct()
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    _listProduct.value = responseBody?.products?.take(3)
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
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
                        _name.value = responseBody?.fullName
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
                }
            })
        }
    }

    private fun getRecommendedJamu() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllJamu()
        client.enqueue(object : Callback<JamuResponse> {

            override fun onResponse(call: Call<JamuResponse>, response: Response<JamuResponse>) {
                if (response.isSuccessful) {

                    _isLoading.value = false
                    val responseBody = response.body()

                    _listRecipe.value = responseBody?.data?.take(3)
                }
            }

            override fun onFailure(call: Call<JamuResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}