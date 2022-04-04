package com.example.qadri.dagger.modules

import com.example.qadri.location.ForegroundOnlyLocationService
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *  @author Abdullah Nagori
 */

@Module
internal abstract class ServiceBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMyService(): ForegroundOnlyLocationService
}