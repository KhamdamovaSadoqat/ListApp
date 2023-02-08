package com.software.listapp.data.main.product

import com.google.gson.annotations.SerializedName

data class OfferResponse(

	@field:SerializedName("offers")
	val offers: List<ProductResponse?>? = null

)
