package com.software.listapp.data.main.product

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductApi {

    @GET
    suspend fun product(
        @Url url: String = "offers"
    ): Response<List<ProductResponse>>
}