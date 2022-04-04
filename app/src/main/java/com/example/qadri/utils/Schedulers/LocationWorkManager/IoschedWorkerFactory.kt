package com.example.qadri.utils.Schedulers.LocationWorkManager

import androidx.work.DelegatingWorkerFactory
import com.example.qadri.mvvm.repository.UserRepository
import com.example.qadri.mvvm.room.DAOAccess
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IoschedWorkerFactory @Inject constructor(
    userRepository: UserRepository, daoAccess: DAOAccess
) : DelegatingWorkerFactory() {
    init {
        addFactory(LocationWorkerFactory(daoAccess, userRepository))
    }
}