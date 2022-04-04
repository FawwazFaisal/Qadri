package com.example.qadri.mvvm.room

import androidx.room.TypeConverter
import com.example.qadri.mvvm.model.portfolio.EtbList
import com.example.qadri.mvvm.model.portfolio.LtdList
import com.example.qadri.mvvm.model.portfolio.portfolioList
import com.example.qadri.mvvm.model.previousVisits.GetPreviousVisit
import com.example.qadri.mvvm.model.trainingAndQuiz.Material
import com.example.qadri.mvvm.model.trainingAndQuiz.Training
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<GetPreviousVisit>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<GetPreviousVisit>::class.java).toList()

    @TypeConverter
    fun listToJsonMaterial(value: List<Material>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListMaterial(value: String) = Gson().fromJson(value, Array<Material>::class.java).toList()

    @TypeConverter
    fun listToJsonTraining(value: List<Training>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToListTraining(value: String) = Gson().fromJson(value, Array<Training>::class.java).toList()

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
}