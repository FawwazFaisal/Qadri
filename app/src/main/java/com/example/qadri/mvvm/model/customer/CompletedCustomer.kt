package com.example.qadri.mvvm.model.customer

data class CompletedCustomer(
    var account_number: String? = "",
    var account_receivable: AccountReceivable,
    var billing_address: String? = "",
    var booked_order: List<BookedOrder>? = listOf(),
    var city: String? = "",
    var cnic: String? = "",
    var company_name: String? = "",
    var complete_address: String? = "",
    var contact_number: String? = "",
    var customer_category: String? = "",
    var customer_id: String? = "",
    var customer_name: String? = "",
    var customer_referred_by: String? = "",
    var customer_status: String? = "",
    var customer_type: String? = "",
    var delivery_address: String? = "",
    var email_address: String? = "",
    var follow_up: List<FollowUp>? = listOf(),
    var last_booked_order: String? = "",
    var last_receipt_date: String? = "",
    var last_shipped_order_date: String? = "",
    var ntn: String? = "",
    var occupation: String? = "",
    var overall_credit_limit: String? = "",
    var owner_contact: String? = "",
    var owner_email: String? = "",
    var owner_name: String? = "",
    var plan_detail_id: String? = "",
    var previous_visit: List<PreviousVisit>? = listOf(),
    var shipping_address: String? = "",
    var status: String? = "",
    var strn: String? = "",
    var total_outstanding_receivable_balance: String? = "",
    var unit: String? = ""
)