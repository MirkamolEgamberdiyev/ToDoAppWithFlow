package com.mirkamol.flowtodoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: HistoryModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addCreateFood(food: CreateFood)

    @Query("SELECT * FROM createFoods")
    fun getCreateFood(): Flow<List<CreateFood>>

    @Query("SELECT * FROM food")
    fun getFoodHistory(): Flow<List<HistoryModel>>

}