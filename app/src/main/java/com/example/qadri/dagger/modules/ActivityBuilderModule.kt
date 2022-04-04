package com.example.qadri.dagger.modules

import com.example.qadri.ui.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *  @author Abdullah Nagori
 */

@Module
interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeDockActivity(): DockActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeChangePasswordActivity(): ChangePasswordActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeSplashActivity(): SplashActivity
}