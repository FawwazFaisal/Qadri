package com.example.qadri.dagger.modules

import com.example.qadri.mvvm.room.ABLDatabase
import com.example.qadri.mvvm.room.DAOAccess
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *  @author Abdullah Nagori
 */

@Module
object AppDaoModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideMyDao(myDB: ABLDatabase): DAOAccess {
        return myDB.leadDao()
    }
}