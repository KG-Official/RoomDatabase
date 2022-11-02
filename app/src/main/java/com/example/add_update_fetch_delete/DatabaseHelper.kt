package com.example.add_update_fetch_delete

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun productDao() :ProductDao

    companion object{
        @Volatile
        private var INSTANCE : DatabaseHelper? = null
        fun getDatabase(context: Context):DatabaseHelper{
            if (INSTANCE == null)
            {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,DatabaseHelper::class.java,"myDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}