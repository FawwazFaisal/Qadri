package com.example.qadri.mvvm.model.dashboard

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Box2")
data class Box2(
    val achievement: Int,
    val target: Int
)
{
    @PrimaryKey
        (autoGenerate = true)
    var id: Int? = null
}