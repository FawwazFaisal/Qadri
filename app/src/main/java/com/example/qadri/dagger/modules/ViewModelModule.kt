package com.example.qadri.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qadri.keys.ViewModelKey
import com.example.qadri.mvvm.viewModel.BaseViewModel
import com.example.qadri.mvvm.viewModel.UserViewModel
import com.example.qadri.mvvm.viewModel.ViewModelFactory
import com.example.qadri.mvvm.viewModel.coroutine.CoroutineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *  @author Abdullah Nagori
 */

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    fun bindBaseViewModel(baseViewModel: BaseViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoroutineViewModel::class)
    fun bindCoroutineViewModel(coroutineViewModel: CoroutineViewModel): ViewModel


}