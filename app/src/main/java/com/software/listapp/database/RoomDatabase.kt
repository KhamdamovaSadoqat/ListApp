package com.software.listapp.database

import androidx.room.Database
import com.software.listapp.domain.main.product.ProductAttributesEntity
import com.software.listapp.domain.main.product.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        ProductAttributesEntity::class
    ], version = 2, exportSchema = true
)
abstract class RoomDatabase: androidx.room.RoomDatabase() {

    abstract val productDao: ProductDao

}