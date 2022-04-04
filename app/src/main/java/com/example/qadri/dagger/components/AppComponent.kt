package com.example.qadri.dagger.components

import android.app.Application
import com.example.qadri.App
import com.example.qadri.dagger.modules.*
import com.example.qadri.mvvm.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *  @author Abdullah Nagori
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    AppDbModule::class,
    AppDaoModule::class,
    ActivityBuilderModule::class,
    ApplicationContextModule::class,
    ServiceBuilderModule::class])

interface AppComponent {

    fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}