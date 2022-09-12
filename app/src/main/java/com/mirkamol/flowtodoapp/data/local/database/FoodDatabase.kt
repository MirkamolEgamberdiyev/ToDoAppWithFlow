package com.mirkamol.flowtodoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mirkamol.flowtodoapp.data.local.dao.FoodDao
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.data.local.entity.Food
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel

@Database(entities = [HistoryModel::class, Food::class, CreateFood::class], version = 1, exportSchema = false)
abstract class FoodDatabase:RoomDatabase() {
    abstract fun historyDao(): FoodDao
}