package com.example.qadri.dagger.modules

import androidx.work.DelegatingWorkerFactory
import com.example.qadri.mvvm.repository.UserRepository
import com.example.qadri.mvvm.room.DAOAccess
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyWorkerFactory @Inject constructor(
    userRepository: UserRepository,
    daoAccess: DAOAccess
) : DelegatingWorkerFactory() {
    init {
        addFactory(MyWorkerFactory(userRepository,daoAccess))
        // Add here other factories that you may need in your application
    }
}