package com.software.listapp.domain.main.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductAttributesEntity(
    val productId: Int,
    val name: String,
    val value: String,
    @PrimaryKey
    val id: String
)
