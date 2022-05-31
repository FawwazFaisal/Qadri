package com.example.qadri.hilt.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.qadri.mvvm.network.Api
import com.example.qadri.utils.SharedPrefManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

class AppEntryPointModule {
    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface BaseRepositoryEntryPoint {
        fun provideSharedPrefs(): SharedPrefManager
    }
}