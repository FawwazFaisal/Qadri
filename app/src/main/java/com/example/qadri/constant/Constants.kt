package com.example.qadri.constant

import android.util.Base64

object Constants {

    //Drawer Menu:
    const val NODE_DASHBOARD : String = "Dashboard"
    const val NODE_CREATE_ORDER : String = "Create Order"
    const val NODE_SALES_PLAN : String = "Sales Plan"
    const val SUB_NODE_TODAY : String = "Today's Plan"
    const val SUB_NODE_PENDING : String = "Pending Visits"
    const val SUB_NODE_COMPLETED : String = "Completed Visits"
    const val SUB_NODE_OTHER_VISITS : String = "Other Visits"
    const val NODE_CUSTOMERS : String = "Customers"
    const val NODE_BANK_DEPOSIT : String = "Bank Deposits"
    const val NODE_ORDERS : String = "Orders"
    const val SUB_NODE_PENDING_ORDER : String = "Pending"
    const val SUB_NODE_IN_TRANSIT_ORDER : String = "In Transit"
    const val SUB_NODE_COMPLETED_ORDER : String = "Completed"
    const val NODE_REPORTS : String = "Reports"
    const val SUB_NODE_REPORTS_VISIT : String = "Visit"
    const val SUB_NODE_REPORTS_RECOVERY : String = "Recovery"
    const val SUB_NODE_REPORTS_ORDER : String = "Order"
    const val SUB_NODE_REPORTS_AGING : String = "Aging"
    const val SUB_NODE_REPORTS_BANK_DEPOSIT : String = "Bank Deposit"
    const val SUB_NODE_REPORTS_COMPLAINTS : String = "Complaints"
    const val SUB_NODE_REPORTS_SALES_PLAN : String = "Sales Plan Report"
    const val NODE_NOTIFICATIONS : String = "Notifications"
    const val NODE_SETTINGS : String = "Settings"
    const val SUB_NODE_CHANGE_PASSWORD ="Change Password"
    const val NODE_LOGOUT : String = "Logout"


    //Hardcoded Strings
    const val DATABASE_NAME : String = "Qadri_DB"
    const val NETWORK_ERROR : String = "Network Error"


    //API Tags
    const val CROSS_SELL: String = "CROSS_SELL"
    const val ADC: String = "ADC"
    const val CONTROL_REPORT: String = "CONTROL_REPORT"
    const val KAFALAH_REPORT: String = "KAFALAH_REPORT"
    const val TIERWISEDEPOSIT: String = "TIERWISEDEPOSIT"
    const val CHANGE_PASSWORD ="CHANGE_PASSWORD"
    const val WEALTH: String = "WEALTH"
    const val LOGIN : String = "LOGIN"
    const val VERIFY_OTP : String = "VERIFY_OTP"
    const val RESET_PWD_REQ : String = "RESET_PWD_REQ"
    const val VERIFY_PWD_REQ : String = "VERIFY_PWD_REQ"
    const val USER_DETAIL : String = "USER_DETAIL"
    const val MARK_ATTENDANCE : String = "MARK_ATTENDANCE"
    const val GET_DYNAMIC_LEADS : String = "GET_DYNAMIC_LEADS"
    const val UPDATE_LOCATION = "UPDATE_LOCATION"
    const val CUSTOMER_TAGGING = "CUSTOMER_TAGGING"
    const val DASHBOARD_COUNT : String = "DASHBOARD_COUNT"
    const val NOTIFICATION : String = "NOTIFICATION"
    const val MATERIAL  = "MATERIAL"
    const val SUBMIT_QUIZ  = "SUBMIT_QUIZ"
    const val JOINT_VISIT_API  = "JOINT_VISIT_API"
    const val NOTIFICATIONS_RESULT : String = "NOTIFICATIONS_RESULT"
    const val TRAINING_RESULT : String = "TRAINING_RESULT"
    const val HALL_OF_FAME : String = "HALL_OF_FAME"
    const val NOTIFICATIONS : String = "NOTIFICATIONS"
    const val BIRTHDAY_NOTIFICATION : String = "BIRTHDAY_NOTIFICATION"
    const val UPLOAD_PHOTO : String = "UPLOAD_PHOTO"
    const val DEPOSIT_REPORTS : String = "DEPOSIT_REPORTS"
    const val MOVEMENT_REPORTS : String = "MOVEMENT_REPORTS"
    const val TOP150CUSTOMERS : String = "TOP150CUSTOMERS"

    //Formats
    val DATE_FORMAT_2 = "yyyy-MM-ddHH:mm:ss"
    val DATE_FORMAT_3 = "MMM d, yyyy K:mm a"
    val TIME_FORMAT_1 = "HH:mm:ss"
    val TIME_FORMAT_2 = "K:mm a"

    //Keys
    const val SYNC_LOCATION ="SYNC_LOCATION"
    const val SYNC_UPLOADED ="SYNC_UPLOADED"
    const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"
    const val FIRST_TIME = "FIRST_TIME"
    const val SHIFT_TIME: String = "SHIFT_TIME"
    const val IS_FINGERPRINT: String = "IS_FINGERPRINT"

    //Bundle Tags
    const val VISIT : String = "VISIT"
    const val VISIT_TYPE  = "VISIT_TYPE"


    const val SUBMIT_REPORT  = "submit_branch_visit_report"
    const val CALL : String = "call"
    const val CUSTOMER_TYPE  = "customer_type"
    const val CUSTOMER_NUMBER  = "customer_number"



    //Codes
    const val CAMERA_RESULT_CODE = 0
    const val GALLERY_RESULT_CODE = 1
    const val WORKOUT_COMPLETED_NOTIFICATION_ID = 1

    fun decode(value: String): String {
        val bytes = Base64.decode(value, Base64.DEFAULT)
        return if(value.isNotEmpty())
            String(bytes)
        else{
            ""
        }
    }

    fun encode(value:String):String{
        val bytes = Base64.encode(value.toByteArray(), Base64.DEFAULT)
        return String(bytes)
    }

}