package com.example.add_update_fetch_delete

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)


    @Query("DELETE FROM Product WHERE productId == :productId")
    fun delProduct(productId:Long)


    @Query("SELECT * FROM Product WHERE productId == :productId")
    fun getUpdateProduct(productId:Long):  LiveData<List<Product>>

    @Query("SELECT * FROM Product")
    fun getProduct() : LiveData<List<Product>>



}