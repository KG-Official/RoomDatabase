package com.example.add_update_fetch_delete

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var productId:Long,
    var productName:String,
    var productPrice:Long,
    var productQuantity: Long
)
