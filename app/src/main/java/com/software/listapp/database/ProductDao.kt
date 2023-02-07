package com.software.listapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.software.listapp.domain.main.product.ProductAttributesEntity
import com.software.listapp.domain.main.product.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductAttributes(productAttributesEntity: ProductAttributesEntity)

    @Query("Select * from `ProductEntity`")
    fun getAllProducts(): LiveData<List<ProductEntity>>
}