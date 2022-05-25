package com.example.qadri

import android.app.Application
import androidx.work.Configuration
import com.example.qadri.dagger.components.AppComponent
import com.example.qadri.dagger.components.DaggerAppComponent
import com.example.qadri.dagger.modules.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector, Configuration.Provider {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var workerConfiguration: Configuration

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return workerConfiguration
    }

}