package com.example.qadri.mvvm.room
import androidx.room.Query
import com.example.qadri.mvvm.model.customer.CustomerResponse
import com.example.qadri.mvvm.repository.BaseRepository
import com.example.qadri.security.EncryptionKeyStoreImpl
import javax.inject.Inject
import kotlin.collections.ArrayList


class RoomHelper @Inject constructor(private val daoAccess: DAOAccess, private val ablDatabase: ABLDatabase) {

    val encryptionKeyStore = EncryptionKeyStoreImpl.instance

    fun insertCustomerData(dynamicLeadsItem: CustomerResponse) {
        daoAccess.insertCustomerData(dynamicLeadsItem)
    }

    fun deleteCustomersData() {
        daoAccess.deleteCustomerData()
    }

    fun clearDB() {
        ablDatabase.clearAllTables()
    }

}