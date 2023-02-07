package com.software.listapp.data.main.product

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("merchant")
	val merchant: String? = null,

	@field:SerializedName("attributes")
	val attributes: List<AttributesItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("brand")
	val brand: String? = null
)

data class Image(

	@field:SerializedName("width")
	val width: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: String? = null
)

data class AttributesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)
