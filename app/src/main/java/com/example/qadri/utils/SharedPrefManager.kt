package com.example.qadri.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.qadri.constant.Constants
import com.example.qadri.mvvm.model.lov.CompanyLeadSource
import com.example.qadri.mvvm.model.lov.CompanyLeadStatu
import com.example.qadri.mvvm.model.lov.CompanyProduct
import com.example.qadri.mvvm.model.lov.CompanyVisitStatu
import com.example.qadri.mvvm.model.userDetail.UserDetailsResponse
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject


class SharedPrefManager @Inject constructor(private val context: Context) {

    private val Key_Pref = "Key_Pref"
    private val TOKEN = "KEY_TOKEN"
    private val USERNAME = "KEY_USERNAME"
    private val KEY_SHIFT_START = "KEY_SHIFT_START"
    private val KEY_USER_DETAILS = "KEY_USER_DETAILS"
    private val KEY_LEAD_STATUS = "KEY_LEAD_STATUS"
    private val KEY_COMPANY_PRODUCTS = "KEY_COMPANY_PRODUCTS"
    private val KEY_VISIT_STATUS = "KEY_VISIT_STATUS"
    private val KEY_LEAD_SOURCE = "KEY_LEAD_SOURCE"

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key_Pref, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun setToken(token: String): Boolean{
        editor.putString(TOKEN, token);
        editor.apply();
        return true;
    }

    fun setUsername(username: String){
        editor.putString(USERNAME, username);
        editor.apply();
    }

    fun getUsername(): String{
        return sharedPreferences.getString(USERNAME,"")!!
    }

    fun getToken():String{
        return sharedPreferences.getString(TOKEN,"")!!
    }

    fun setShiftStart(isShiftStart: Boolean): Boolean{
        editor.putBoolean(KEY_SHIFT_START, isShiftStart);
        editor.apply();
        return true;
    }

    fun getShiftStart():Boolean{
        return sharedPreferences.getBoolean(KEY_SHIFT_START,false)
    }

    fun setUserDetails(user: UserDetailsResponse): Boolean {
        editor.putString(KEY_USER_DETAILS, GsonFactory.getConfiguredGson()?.toJson(user))
        editor.apply();
        return true;
    }

    fun getUserDetails(): UserDetailsResponse? {
        val json = sharedPreferences.getString(KEY_USER_DETAILS, "")
        return GsonFactory.getConfiguredGson()?.fromJson(json, UserDetailsResponse::class.java)
    }

    fun setLeadStatus(leadStatus: List<CompanyLeadStatu>): Boolean {
        editor.putString(KEY_LEAD_STATUS, GsonFactory.getConfiguredGson()?.toJson(leadStatus))
        editor.apply();
        return true;
    }

    fun getLeadStatus():List<CompanyLeadStatu>? {
        val json = sharedPreferences.getString(KEY_LEAD_STATUS, "")
        val listType: Type = object : TypeToken<List<CompanyLeadStatu>>() {}.type
        return GsonFactory.getConfiguredGson()?.fromJson(json, listType)
    }

    fun setCompanyProducts(companyProducts: List<CompanyProduct>): Boolean {
        editor.putString(KEY_COMPANY_PRODUCTS, GsonFactory.getConfiguredGson()?.toJson(companyProducts))
        editor.apply();
        return true;
    }

    fun getCompanyProducts():List<CompanyProduct>? {
        val json = sharedPreferences.getString(KEY_COMPANY_PRODUCTS, "")
        val listType: Type = object : TypeToken<List<CompanyProduct>>() {}.type
        return GsonFactory.getConfiguredGson()?.fromJson(json, listType)
    }

    fun setVisitStatus(visitStatus: List<CompanyVisitStatu>): Boolean {
        editor.putString(KEY_VISIT_STATUS, GsonFactory.getConfiguredGson()?.toJson(visitStatus))
        editor.apply();
        return true;
    }

    fun setLeadSource(leadSource: List<CompanyLeadSource>): Boolean {
        editor.putString(KEY_LEAD_SOURCE, GsonFactory.getConfiguredGson()?.toJson(leadSource))
        editor.apply();
        return true;
    }

    fun getLeadSource():List<CompanyLeadSource>? {
        val json = sharedPreferences.getString(KEY_LEAD_SOURCE, "")
        val listType: Type = object : TypeToken<List<CompanyLeadSource>>() {}.type
        return GsonFactory.getConfiguredGson()?.fromJson(json, listType)
    }

    fun getVisitStatus():List<CompanyVisitStatu>? {
        val json = sharedPreferences.getString(KEY_VISIT_STATUS, "")
        val listType: Type = object : TypeToken<List<CompanyVisitStatu>>() {}.type
        return GsonFactory.getConfiguredGson()?.fromJson(json, listType)
    }

    fun logout(): Boolean {
        editor.clear()
        editor.apply()
        return true
    }

    fun clear(): Boolean {
        editor.clear()
        editor.apply()
        return true
    }

    fun setTime(currentDate: String) {
        editor.putString(Constants.SHIFT_TIME, currentDate)
        editor.apply();
    }

    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        SharedPrefKeyManager.preferences.edit().putString(key, jsonString).apply()
    }


    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = SharedPrefKeyManager.preferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}