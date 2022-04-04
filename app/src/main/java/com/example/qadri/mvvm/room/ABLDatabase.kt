package com.example.qadri.mvvm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.qadri.constant.Constants
import com.example.qadri.mvvm.model.addLead.DynamicLeadsItem
import com.example.qadri.mvvm.model.checkin.CheckinModel
import com.example.qadri.mvvm.model.location.UserLocation
import com.example.qadri.mvvm.model.lov.CompanyLeadStatu
import com.example.qadri.mvvm.model.lov.CompanyProduct
import com.example.qadri.mvvm.model.lov.CompanyVisitStatu
import com.example.qadri.mvvm.model.portfolio.portfolioList
import com.example.qadri.mvvm.model.previousVisits.GetPreviousVisit

@Database(entities = [
    DynamicLeadsItem::class,
    CompanyProduct::class,
    CompanyVisitStatu::class,
    CompanyLeadStatu::class,
    GetPreviousVisit::class,
    CheckinModel::class,
    UserLocation::class,
    portfolioList::class], version = 22, exportSchema = false)

@TypeConverters(Converters::class)
abstract class ABLDatabase : RoomDatabase() {

    abstract fun leadDao(): DAOAccess

    companion object {

        const val DATABASE_NAME = Constants.DATABASE_NAME

        @Volatile // All threads have immediate access to this property
        private var instance: ABLDatabase? = null

        private val LOCK = Any() // Makes sure no threads making the same thing at the same time

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ABLDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun getInstance(activityContext: Context): ABLDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(activityContext.applicationContext).also { instance = it }
        }
    }
}