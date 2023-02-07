package com.software.listapp.data.main.product

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.software.listapp.database.ProductDao
import com.software.listapp.domain.base.BaseResultList
import com.software.listapp.domain.main.product.ProductAttributesEntity
import com.software.listapp.domain.main.product.ProductEntity
import com.software.listapp.domain.main.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao
):ProductRepository {
    override suspend fun product(): Flow<BaseResultList<List<ProductEntity>, List<ProductAttributesEntity>, List<ProductResponse>>> {
        val response = productApi.product()
        return flow {
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                val listProduct = arrayListOf<ProductEntity>()
                val listProductAttributes = arrayListOf<ProductAttributesEntity>()
                body.forEach{ product ->
                    val productEntity = ProductEntity(
                        product.name?: "",
                        product.image?.width?: "",
                        product.image?.height?: "",
                        product.image?.url?: "",
                        product.merchant?: "",
                        product.category?: "",
                        product.brand?: "",
                        product.id?: -1
                    )
                    listProduct.add(productEntity)

                    product.attributes?.forEach { attr ->
                        val attributesEntity = ProductAttributesEntity(
                            product.id?: -1,
                            attr?.name?: "",
                            attr?.value?: "",
                            "${product.id?: -1}${attr?.name?: ""}"
                        )
                        listProductAttributes.add(attributesEntity)
                    }

                }
                emit(BaseResultList.Success(listProduct, listProductAttributes))
            } else {
                val type = object : TypeToken<List<ProductResponse>>() {}.type
                val err: List<ProductResponse> =
                    Gson().fromJson(response.errorBody()!!.charStream(), type)
                //here should be handled error messages but i don't know they look :(
                emit(BaseResultList.Error(err))
            }
        }
    }

    override suspend fun insertProduct(productEntity: ProductEntity) {
        productDao.insertProduct(productEntity)
    }

    override suspend fun insertProductAttributes(productAttributesEntity: ProductAttributesEntity) {
        productDao.insertProductAttributes(productAttributesEntity)
    }
}