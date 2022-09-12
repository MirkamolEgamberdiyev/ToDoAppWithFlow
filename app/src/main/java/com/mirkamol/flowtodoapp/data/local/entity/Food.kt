package com.mirkamol.flowtodoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    var foodName: String,
    var price: String,
    var quantity: String,
)