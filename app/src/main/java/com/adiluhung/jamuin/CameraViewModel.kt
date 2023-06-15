package com.adiluhung.jamuin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.jamuin.data.network.responses.ScanResponse
import com.adiluhung.jamuin.data.network.retrofit.ApiConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CameraViewModel() : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _prediction = MutableLiveData<String>()
    val prediction: LiveData<String> = _prediction

    fun scanImage(image: File?) {
        _isLoading.value = true
        if (image != null) {
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                image.name,
                image.asRequestBody("image/jpeg".toMediaType())
            )
            val client = ApiConfig.getApiServiceModel().scanImage(imageMultipart)
            client.enqueue(object : Callback<ScanResponse> {
                override fun onResponse(
                    call: Call<ScanResponse>,
                    response: Response<ScanResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _isLoading.value = false
                            _prediction.value = responseBody.prediction
                            Log.d("CameraViewModel", "Prediction: ${responseBody.prediction}")
                        }
                    }
                }

                override fun onFailure(call: Call<ScanResponse>, t: Throwable) {
                    _isLoading.value = false
                    _prediction.value = t.message
                }
            })
        }
    }
}