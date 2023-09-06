package com.d204.algo.di

import com.d204.algo.presentation.utils.Constants
import com.d204.algo.presentation.utils.TokenManager
import com.d204.algo.remote.api.ServiceFactory
import com.d204.algo.remote.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideUserService(tokenManager: TokenManager): UserService {
        return ServiceFactory.createUserService(Constants.DEBUG, Constants.BASE_URL, tokenManager)
    }
}
