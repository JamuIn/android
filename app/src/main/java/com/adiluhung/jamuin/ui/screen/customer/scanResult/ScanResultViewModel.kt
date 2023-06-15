package com.adiluhung.jamuin.ui.screen.customer.scanResult

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.network.responses.JamuItem
import com.adiluhung.jamuin.data.network.responses.JamuResponse
import com.adiluhung.jamuin.data.network.responses.ProductResponse
import com.adiluhung.jamuin.data.network.responses.ProductsItem
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanResultViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoading2 = MutableLiveData<Boolean>()
    val isLoading2: LiveData<Boolean> = _isLoading2

    private val _listArticle = MutableLiveData<List<JamuItem>?>()
    val listArticle: LiveData<List<JamuItem>?> = _listArticle

    private val _listProduct = MutableLiveData<List<ProductsItem>?>()
    val listProduct: LiveData<List<ProductsItem>?> = _listProduct

    fun getListArticle(scanResult: String) {
        val mainIngredient = when (scanResult) {
            "lengkuas" -> 1
            "kunyit" -> 2
            "jahe" -> 3
            else -> 0
        }

        val client = ApiConfig.getApiService().getJamuByIngredient(mainIngredient)
        client.enqueue(object : Callback<JamuResponse> {
            override fun onResponse(call: Call<JamuResponse>, response: Response<JamuResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    _listArticle.postValue(responseBody?.data)
                }
            }

            override fun onFailure(call: Call<JamuResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }


    fun getListProduct(scanResult: String) {
        _isLoading2.value = true
        val client = ApiConfig.getApiService().getAllProduct()
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading2.value = false
                    val responseBody = response.body()
                    _listProduct.postValue(responseBody?.products?.map {
                        if (it.mainIngredient == scanResult) {
                            it
                        } else {
                            null
                        }
                    }?.filterNotNull())
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _isLoading2.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}