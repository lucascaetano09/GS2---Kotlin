package com.example.gs2_92954.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gs2_92954.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}