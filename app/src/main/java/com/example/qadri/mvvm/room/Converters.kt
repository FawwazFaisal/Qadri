package com.example.qadri.mvvm.room

import androidx.room.TypeConverter
import com.example.qadri.mvvm.model.customer.*
import com.example.qadri.mvvm.model.portfolio.EtbList
import com.example.qadri.mvvm.model.portfolio.LtdList
import com.example.qadri.mvvm.model.portfolio.portfolioList
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJsonNTB(value: List<portfolioList>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListNTB(value: String) = Gson().fromJson(value, Array<portfolioList>::class.java).toList()

    @TypeConverter
    fun listToJsonETB(value: List<EtbList>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListETB(value: String) = Gson().fromJson(value, Array<EtbList>::class.java).toList()

    @TypeConverter
    fun listToJsonLTD(value: List<LtdList>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListLTD(value: String) = Gson().fromJson(value, Array<LtdList>::class.java).toList()

    @TypeConverter
    fun listToJsonBookedOrder(value: List<BookedOrder>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListBookedOrder(value: String) = Gson().fromJson(value, Array<BookedOrder>::class.java).toList()

    @TypeConverter
    fun listToJsonFollowup(value: List<FollowUp>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListFollowup(value: String) = Gson().fromJson(value, Array<FollowUp>::class.java).toList()

    @TypeConverter
    fun listToJsonPreviousVisit(value: List<PreviousVisit>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListPreviousVisit(value: String) = Gson().fromJson(value, Array<PreviousVisit>::class.java).toList()

    @TypeConverter
    fun listToJsonAllCustomer(value: List<AllCustomer>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListAllCustomer(value: String) = Gson().fromJson(value, Array<AllCustomer>::class.java).toList()

    @TypeConverter
    fun listToJsonCompletedCustomer(value: List<CompletedCustomer>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListCompletedCustomer(value: String) = Gson().fromJson(value, Array<CompletedCustomer>::class.java).toList()

    @TypeConverter
    fun listToJsonOtherCustomer(value: List<OtherCustomer>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListOtherCustomer(value: String) = Gson().fromJson(value, Array<OtherCustomer>::class.java).toList()

    @TypeConverter
    fun listToJsonPendingCustomer(value: List<PendingCustomer>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListPendingCustomer(value: String) = Gson().fromJson(value, Array<PendingCustomer>::class.java).toList()

    @TypeConverter
    fun listToJsonTodayPlan(value: List<TodayPlan>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListTodayPlan(value: String) = Gson().fromJson(value, Array<TodayPlan>::class.java).toList()

}