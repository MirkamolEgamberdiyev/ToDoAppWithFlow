package com.mirkamol.flowtodoapp.repository

import com.mirkamol.flowtodoapp.data.local.dao.FoodDao
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val foodDao: FoodDao){

    suspend fun addFood(food:HistoryModel) =foodDao.addFood(food)
    fun getFoodHistory() =foodDao.getFoodHistory()
    suspend fun addCreateFood(food: CreateFood) = foodDao.addCreateFood(food)
    fun getCreateFood() = foodDao.getCreateFood()

}