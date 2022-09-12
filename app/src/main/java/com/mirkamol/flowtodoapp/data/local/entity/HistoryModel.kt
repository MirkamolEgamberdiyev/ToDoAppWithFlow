package com.mirkamol.flowtodoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "food")
data class HistoryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val count:String,
    val foodName: String,
    val price: String,
    val quantity: String,
)
