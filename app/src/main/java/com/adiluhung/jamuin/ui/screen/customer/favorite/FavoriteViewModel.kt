package com.adiluhung.jamuin.ui.screen.customer.favorite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.FavoriteResponse
import com.adiluhung.jamuin.data.network.responses.FavoritesItem
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(private val pref: UserPreferences) : ViewModel() {

    private val token = runBlocking {
        pref.getUserToken()
            .map { token -> token }
            .first()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _listFavorite = MutableLiveData<List<FavoritesItem>>()
    val listFavorite: MutableLiveData<List<FavoritesItem>> = _listFavorite

    init {
        getUserFavorite()
    }

    private fun getUserFavorite() {
        _isLoading.value = true
        if (token != null) {
            val client = ApiConfig.getApiService().getUserFavorite("Bearer $token")
            client.enqueue(object : Callback<FavoriteResponse> {
                override fun onResponse(
                    call: Call<FavoriteResponse>, response: Response<FavoriteResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val responseBody = response.body()
                        _listFavorite.value = responseBody?.favorites
                    }
                }

                override fun onFailure(call: Call<FavoriteResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
                }
            })
        }
    }
}