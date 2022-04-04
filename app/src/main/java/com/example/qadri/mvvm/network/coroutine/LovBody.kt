package com.example.qadri.mvvm.network.coroutine

import com.example.qadri.mvvm.model.lov.CompanyLeadSource
import com.example.qadri.mvvm.model.lov.CompanyLeadStatu
import com.example.qadri.mvvm.model.lov.CompanyProduct
import com.example.qadri.mvvm.model.lov.CompanyVisitStatu

data class LovBody(
    val company_lead_source: List<CompanyLeadSource>,
    val company_lead_status: List<CompanyLeadStatu>,
    val company_products: List<CompanyProduct>,
    val company_visit_status: List<CompanyVisitStatu>
)