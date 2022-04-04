package com.example.qadri.mvvm.model.lov

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LovLeadStatus")
data class  CompanyLeadStatu(
    @ColumnInfo(name = "company_id")
    val company_id: String,
    @ColumnInfo(name = "created_at")
    val created_at: String,
    @ColumnInfo(name = "created_by")
    val created_by: String,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "record_id")
    val record_id: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "updated_at")
    val updated_at: String
)

{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var Id: Int? = null
}