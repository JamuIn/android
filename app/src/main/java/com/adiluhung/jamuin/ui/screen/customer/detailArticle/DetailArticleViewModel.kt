package com.adiluhung.jamuin.ui.screen.customer.detailArticle

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.local.UserPreferences
import com.adiluhung.jamuin.data.network.responses.AddFavoriteResponse
import com.adiluhung.jamuin.data.network.responses.DetailJamuResponse
import com.adiluhung.jamuin.data.network.responses.FavoriteResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailArticleViewModel(private val pref: UserPreferences) : ViewModel() {
    private val token = runBlocking {
        pref.getUserToken()
            .map { token -> token }
            .first()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _article = MutableLiveData<DetailJamuResponse>()
    val article: LiveData<DetailJamuResponse> = _article

    private val _isFavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean> = _isFavorited

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getDetailArticle(articleId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailJamu(articleId)
        client.enqueue(object : Callback<DetailJamuResponse> {
            override fun onResponse(
                call: Call<DetailJamuResponse>,
                response: Response<DetailJamuResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _article.value = response.body()
                }
            }

            override fun onFailure(call: Call<DetailJamuResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("DetailArticleViewModel", "getDetailArticle: ${t.message}")
            }

        })
    }

    fun checkIsFavorited(articleId: Int) {
        val client = ApiConfig.getApiService().getUserFavorite("Bearer $token")
        client.enqueue(object : Callback<FavoriteResponse> {
            override fun onResponse(
                call: Call<FavoriteResponse>,
                response: Response<FavoriteResponse>
            ) {
                if (response.isSuccessful) {
                    val favoriteList = response.body()?.favorites
                    if (favoriteList != null) {
                        _isFavorited.value = favoriteList.any { it.id == articleId }
                    }
                }
            }

            override fun onFailure(call: Call<FavoriteResponse>, t: Throwable) {
                Log.e("DetailArticleViewModel", "checkIsFavorited: ${t.message}")
            }

        })
    }

    fun addFavorite(articleId: Int) {
        val client = ApiConfig.getApiService().addUserFavorite("Bearer $token", articleId)
        client.enqueue(object : Callback<AddFavoriteResponse> {
            override fun onResponse(
                call: Call<AddFavoriteResponse>,
                response: Response<AddFavoriteResponse>
            ) {
                if (response.isSuccessful) {
                    _isFavorited.value = true
                    _message.value = "Jamu ditambahkan ke favorit"
                }
            }

            override fun onFailure(call: Call<AddFavoriteResponse>, t: Throwable) {
                Log.e("DetailArticleViewModel", "addFavorite: ${t.message}")
            }
        })
    }

    fun deleteFavorite(articleId: Int){
        val client = ApiConfig.getApiService().deleteUserFavorite("Bearer $token", articleId)
        client.enqueue(object : Callback<AddFavoriteResponse> {
            override fun onResponse(
                call: Call<AddFavoriteResponse>,
                response: Response<AddFavoriteResponse>
            ) {
                if (response.isSuccessful) {
                    _isFavorited.value = false
                    _message.value = "Jamu dihapus dari favorit"
                }
            }

            override fun onFailure(call: Call<AddFavoriteResponse>, t: Throwable) {
                Log.e("DetailArticleViewModel", "deleteFavorite: ${t.message}")
            }
        })
    }
}