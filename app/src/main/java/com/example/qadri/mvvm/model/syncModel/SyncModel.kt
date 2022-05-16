package com.example.qadri.mvvm.model.syncModel

import com.example.qadri.mvvm.model.customer.CustomerResponse
import com.example.qadri.mvvm.model.lov.LovResponse

data class SyncModel (
    var lovResponse : LovResponse,
    var customersResponse : CustomerResponse
)