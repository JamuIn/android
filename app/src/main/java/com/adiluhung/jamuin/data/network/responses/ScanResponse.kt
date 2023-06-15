package com.adiluhung.jamuin.data.network.responses

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("prediction")
	val prediction: String
)
