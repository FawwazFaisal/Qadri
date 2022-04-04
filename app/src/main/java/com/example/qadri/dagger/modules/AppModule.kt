package com.example.qadri.dagger.modules

import android.content.Context
import androidx.work.Configuration
import com.example.qadri.mvvm.network.Api
import com.example.qadri.mvvm.room.ABLDatabase
import com.example.qadri.mvvm.room.DAOAccess
import com.example.qadri.mvvm.room.RoomHelper
import com.example.qadri.utils.*
import com.example.qadri.utils.Schedulers.BaseScheduler
import com.example.qadri.utils.Schedulers.LocationWorkManager.IoschedWorkerFactory
import com.example.qadri.utils.Schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *  @author Abdullah Nagori
 */

@Module(includes = [(ViewModelModule::class)])
class AppModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduler(): BaseScheduler {
        return SchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideSharedPrefManager(context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    @Singleton
    fun provideRoomHelper(daoAccess: DAOAccess, ablDatabase: ABLDatabase): RoomHelper {
        return RoomHelper(daoAccess, ablDatabase)
    }

    @Provides
    @Singleton
    fun provideInternetHelper(context: Context): InternetHelper {
        return InternetHelper(context)
    }

    @Provides
    @Singleton
    fun provideValidationHelper(context: Context): ValidationHelper {
        return ValidationHelper(context)
    }

    @Provides
    @Singleton
    fun provideUtilHelper(context: Context): UtilHelper {
        return UtilHelper(context)
    }


    @Provides
    @Singleton
    fun provideDateTimeFormatter(context: Context): DateTimeFormatter {
        return DateTimeFormatter(context)
    }

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        ioschedWorkerFactory: IoschedWorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(ioschedWorkerFactory)
            .build()
    }
}