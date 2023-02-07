package com.software.listapp.domain.main.product

import javax.inject.Inject

class ProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun product() = productRepository.product()

    suspend fun insertProduct(productEntity: ProductEntity) =
        productRepository.insertProduct(productEntity)

    suspend fun insertProductAttributes(productAttributesEntity: ProductAttributesEntity) =
        productRepository.insertProductAttributes(productAttributesEntity)
}