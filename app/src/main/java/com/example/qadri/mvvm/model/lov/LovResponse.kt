package com.example.qadri.mvvm.model.lov

data class LovResponse(
    val company_lead_source: List<CompanyLeadSource>,
    val company_lead_status: List<CompanyLeadStatu>,
    val company_products: List<CompanyProduct>,
    val company_visit_status: List<CompanyVisitStatu>
)