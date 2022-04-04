package com.example.qadri.mvvm.model.portfolio

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Entity(tableName = "Portfolio")
@Parcelize
data class portfolioList(
    @ColumnInfo(name = "account_num")
    val account_num: String?,
    @ColumnInfo(name = "account_opening_date")
    val account_opening_date: String?,
    @ColumnInfo(name = "account_title")
    val account_title: String?,
    @ColumnInfo(name = "address")
    val address: String?,
    @ColumnInfo(name = "amim")
    val amim: String?,
    @ColumnInfo(name = "average_balance")
    val average_balance: String?,
    @ColumnInfo(name = "branch_code")
    val branch_code: String?,
    @ColumnInfo(name = "branch_name")
    val branch_name: String?,
    @ColumnInfo(name = "brrowing_need")
    val brrowing_need: String?,
    @ColumnInfo(name = "ci")
    val ci: String?,
    @ColumnInfo(name = "city")
    val city: String?,
    @ColumnInfo(name = "client_id")
    val client_id: String?,
    @ColumnInfo(name = "comments")
    val comments: String?,
    @ColumnInfo(name = "company_id")
    val company_id: String?,
    @ColumnInfo(name = "company_name")
    val company_name: String?,
    @ColumnInfo(name = "contact_number")
    val contact_number: String?,
    @ColumnInfo(name = "customer_detail_branchname")
    val customer_detail_branchname: String?,
    @ColumnInfo(name = "customer_id")
    val customer_id: String?,
    @ColumnInfo(name = "customer_name")
    val customer_name: String?,
    @ColumnInfo(name = "date_of_birth")
    val date_of_birth: String?,
    @ColumnInfo(name = "date_of_joining")
    val date_of_joining: String?,
    @ColumnInfo(name = "date_received")
    val date_received: String?,
    @ColumnInfo(name = "debit_card")
    val debit_card: String?,
    @ColumnInfo(name = "dependent_address")
    val dependent_address: String?,
    @ColumnInfo(name = "dependent_designation")
    val dependent_designation: String?,
    @ColumnInfo(name = "dependent_name")
    val dependent_name: String?,
    @ColumnInfo(name = "dependent_phone_no")
    val dependent_phone_no: String?,
    @ColumnInfo(name = "dependent_relationship")
    val dependent_relationship: String?,
    @ColumnInfo(name = "designation")
    val designation: String?,
    @ColumnInfo(name = "ease")
    val ease: String?,
    @ColumnInfo(name = "email_address")
    val email_address: String?,
    @ColumnInfo(name = "emp_code")
    val emp_code: String?,
    @ColumnInfo(name = "emp_id")
    val emp_id: String?,
    @ColumnInfo(name = "first_name")
    val first_name: String?,
    @ColumnInfo(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "hf")
    val hf: String?,
    @ColumnInfo(name = "internet_banking")
    val internet_banking: String?,
    @ColumnInfo(name = "kafalah")
    val kafalah: String?,
    @ColumnInfo(name = "login_id")
    val login_id: String?,
    @ColumnInfo(name = "marital_status")
    val marital_status: String?,
    @ColumnInfo(name = "monthly_income")
    val monthly_income: String?,
    @ColumnInfo(name = "no_of_kids")
    val no_of_kids: String?,
    @ColumnInfo(name = "occupation")
    val occupation: String?,
    @ColumnInfo(name = "other_income")
    val other_income: String?,
    @ColumnInfo(name = "priority")
    val priority: String?,
    @ColumnInfo(name = "profile_img")
    val profile_img: String?,
    @ColumnInfo(name = "region_name")
    val region_name: String?,
    @ColumnInfo(name = "role_name")
    val role_name: String?,
    @ColumnInfo(name = "saving_need")
    val saving_need: String?,
    @ColumnInfo(name = "sector")
    val sector: String?,
    @ColumnInfo(name = "security_need")
    val security_need: String?,
    @ColumnInfo(name = "sms_alerts")
    val sms_alerts: String?,
    @ColumnInfo(name = "source_of_income")
    val source_of_income: String?,
    @ColumnInfo(name = "spending")
    val spending: String?,
    @ColumnInfo(name = "spoues")
    val spoues: String?,
    @ColumnInfo(name = "transactional_need")
    val transactional_need: String?,
    @ColumnInfo(name = "vehicle")
    val vehicle: String?,
    @ColumnInfo(name = "customer_type")
    val customer_type: String?
): Parcelable, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0
}