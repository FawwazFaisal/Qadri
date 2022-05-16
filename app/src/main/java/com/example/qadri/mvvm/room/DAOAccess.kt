package com.example.qadri.mvvm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qadri.mvvm.model.customer.CustomerResponse


@Dao
interface DAOAccess {

    /** Customer Query */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomerData(customerResponse: CustomerResponse)

    @Query("SELECT * FROM Customers ")
    fun getCustomerData(): CustomerResponse

    @Query("DELETE from Customers")
    fun deleteCustomerData()

}