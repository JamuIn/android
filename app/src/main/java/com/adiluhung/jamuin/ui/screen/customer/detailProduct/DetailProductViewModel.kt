package com.adiluhung.jamuin.ui.screen.customer.detailProduct

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.AddCartResponse
import com.adiluhung.jamuin.data.network.responses.DetailProductResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductViewModel(private val pref: UserPreferences) : ViewModel() {
    private val token = runBlocking {
        pref.getUserToken()
            .map { token -> token }
            .first()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: MutableLiveData<String> = _message

    private val _product = MutableLiveData<DetailProductResponse>()
    val product: MutableLiveData<DetailProductResponse> = _product

    fun getDetailProduct(productId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailProduct(productId)
        client.enqueue(object : Callback<DetailProductResponse> {
            override fun onResponse(
                call: Call<DetailProductResponse>,
                response: Response<DetailProductResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _product.value = response.body()
                }
            }

            override fun onFailure(call: Call<DetailProductResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("DetailArticleViewModel", "getDetailArticle: ${t.message}")
            }

        })
    }

    fun addUserCart(productId: Int, quantity: Int){
        _isLoading.value = true
        val client = ApiConfig.getApiService().addUserCart("Bearer $token", productId, quantity)
        client.enqueue(object: Callback<AddCartResponse>{
            override fun onResponse(
                call: Call<AddCartResponse>,
                response: Response<AddCartResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _message.value = "Data berhasil ditambahkan ke keranjang"
                }
            }

            override fun onFailure(call: Call<AddCartResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("DetailProductViewModel", "addUserCart: ${t.message}")
            }
        })
    }
}