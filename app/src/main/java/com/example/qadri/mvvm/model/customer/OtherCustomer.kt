package com.example.qadri.mvvm.model.customer

data class OtherCustomer(
    var account_number: String? = "",
    var account_receivable: List<String>? = listOf(),
    var billing_address: String? = "",
    var booked_order: List<String>? = listOf(),
    var city: String? = "",
    var cnic: String? = "",
    var company_name: String? = "",
    var complete_address: String? = "",
    var customer_category: String? = "",
    var customer_id: String? = "",
    var customer_name: String? = "",
    var customer_referred_by: String? = "",
    var customer_type: String? = "",
    var delivery_address: String? = "",
    var email_address: String? = "",
    var follow_up: List<String>? = listOf(),
    var last_booked_order: String? = "",
    var last_receipt_date: String? = "",
    var last_shipped_order_date: String? = "",
    var ntn: String? = "",
    var occupation: String? = "",
    var overall_credit_limit: String? = "",
    var owner_contact: String? = "",
    var owner_email: String? = "",
    var owner_name: String? = "",
    var previous_visit: List<PreviousVisit>? = listOf(),
    var shipping_address: String? = "",
    var status: String? = "",
    var strn: String? = "",
    var total_outstanding_receivable_balance: String? = "",
    var unit: String? = ""
)