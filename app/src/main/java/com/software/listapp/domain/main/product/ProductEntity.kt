package com.software.listapp.domain.main.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(

    val name: String,
    val imageWidth: String,
    val imageHeight: String,
    val imageUrl: String,
    val merchant: String,
    val category: String,
    val brand: String,
    @PrimaryKey
    val id: Int
)
