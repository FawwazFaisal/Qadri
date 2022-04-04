package com.example.qadri.mvvm.model.dashboard

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DashboardCount")
data class DashboardResponse(
    @ColumnInfo(name = "box_1")
    val box_1: Box1,
    @ColumnInfo(name = "box_2")
    val box_2: Box2,
    @ColumnInfo(name = "box_3")
    val box_3: Box3,
    @ColumnInfo(name = "today_calls")
    val today_calls: Int,
    @ColumnInfo(name = "today_followups")
    val today_followups: Int,
    @ColumnInfo(name = "today_visits")
    val today_visits: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}