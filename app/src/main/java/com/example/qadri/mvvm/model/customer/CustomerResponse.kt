package com.example.qadri.mvvm.model.customer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customers")
data class CustomerResponse(
    @ColumnInfo(name = "all_customer")
    val all_customer: List<AllCustomer>,
    @ColumnInfo(name = "completed_customer")
    val completed_customer: List<CompletedCustomer>,
    @ColumnInfo(name = "other_customer")
    val other_customer: List<OtherCustomer>,
    @ColumnInfo(name = "pending_customer")
    val pending_customer: List<PendingCustomer>,
    @ColumnInfo(name = "today_plan")
    val today_plan: List<TodayPlan>
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var Id: Int? = null
}
