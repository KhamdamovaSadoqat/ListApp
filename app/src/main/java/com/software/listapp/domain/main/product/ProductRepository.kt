package com.software.listapp.domain.main.product

import com.software.listapp.data.main.product.OfferResponse
import com.software.listapp.domain.base.BaseResultList
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun product(): Flow<BaseResultList<List<ProductEntity>, List<ProductAttributesEntity>, OfferResponse>>

    suspend fun insertProduct(productEntity: ProductEntity)

    suspend fun insertProductAttributes(productAttributesEntity: ProductAttributesEntity)
}