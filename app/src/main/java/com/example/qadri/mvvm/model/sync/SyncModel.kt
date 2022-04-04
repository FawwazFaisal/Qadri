package com.example.qadri.mvvm.model.sync

import com.example.qadri.mvvm.model.addLead.DynamicLeadsItem
import com.example.qadri.mvvm.model.checkin.CheckinModel
import com.example.qadri.mvvm.model.lov.LovResponse
import com.example.qadri.mvvm.model.portfolio.PortfolioResponse

data class SyncModel(
    var dynamicList: ArrayList<DynamicLeadsItem>?,
    var lovResponse: LovResponse,
    var visitCallResponse: ArrayList<CheckinModel>?,
    var portfolioResponse: PortfolioResponse
)