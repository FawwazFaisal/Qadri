package com.example.qadri.hilt.modules

import android.content.Context
import androidx.room.Room
import com.example.qadri.mvvm.network.Api
import com.example.qadri.mvvm.repository.UserRepository
import com.example.qadri.mvvm.room.ABLDatabase
import com.example.qadri.mvvm.room.DAOAccess
import com.example.qadri.mvvm.room.RoomHelper
import com.example.qadri.utils.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *  @author Abdullah Nagori
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    @Singleton
    fun provideRoomHelper(daoAccess: DAOAccess, ablDatabase: ABLDatabase): RoomHelper {
        return RoomHelper(daoAccess, ablDatabase)
    }

    @Provides
    @Singleton
    fun provideInternetHelper(@ApplicationContext context: Context): InternetHelper {
        return InternetHelper(context)
    }

    @Provides
    @Singleton
    fun provideValidationHelper(@ApplicationContext context: Context): ValidationHelper {
        return ValidationHelper(context)
    }

    @Provides
    @Singleton
    fun provideUtilHelper(@ApplicationContext context: Context): UtilHelper {
        return UtilHelper(context)
    }


    @Provides
    @Singleton
    fun provideDateTimeFormatter(@ApplicationContext context: Context): DateTimeFormatter {
        return DateTimeFormatter(context)
    }

    @Provides
    @Singleton
    fun provideMyDao(myDB: ABLDatabase): DAOAccess {
        return myDB.leadDao()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): ABLDatabase {
        return Room.databaseBuilder(
            context,
            ABLDatabase::class.java, ABLDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: Api, @ApplicationContext context: Context): UserRepository =
        UserRepository(api, context)
}