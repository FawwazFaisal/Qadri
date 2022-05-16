package com.example.qadri.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.qadri.constant.Constants
import com.example.qadri.mvvm.model.lov.Category
import com.example.qadri.mvvm.model.lov.Department
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
    private val KEY_CATEGORIES = "KEY_CATEGORIES"
    private val KEY_DEPARTMENT = "KEY_DEPARTMENT"
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

    fun setCategories(category: List<Category>): Boolean {
        editor.putString(KEY_CATEGORIES, GsonFactory.getConfiguredGson()?.toJson(category))
        editor.apply();
        return true;
    }

    fun getCategories():List<Category>? {
        val json = sharedPreferences.getString(KEY_CATEGORIES, "")
        val listType: Type = object : TypeToken<List<Category>>() {}.type
        return GsonFactory.getConfiguredGson()?.fromJson(json, listType)
    }

    fun setDepartment(department: List<Department>): Boolean {
        editor.putString(KEY_DEPARTMENT, GsonFactory.getConfiguredGson()?.toJson(department))
        editor.apply();
        return true;
    }

    fun getDepartment():List<Department>? {
        val json = sharedPreferences.getString(KEY_DEPARTMENT, "")
        val listType: Type = object : TypeToken<List<Department>>() {}.type
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