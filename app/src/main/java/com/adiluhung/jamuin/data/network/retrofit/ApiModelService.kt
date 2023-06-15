package com.adiluhung.jamuin.data.network.retrofit

import com.adiluhung.jamuin.data.network.responses.ScanResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiModelService {
    @Multipart
    @POST("/")
    fun scanImage(
        @Part file: MultipartBody.Part
    ): Call<ScanResponse>
}