package com.d204.algo.di

import com.d204.algo.data.repository.remote.RankingRemote
import com.d204.algo.data.repository.remote.UserRemote
import com.d204.algo.presentation.utils.Constants
import com.d204.algo.presentation.utils.TokenManager
import com.d204.algo.remote.RankingRemoteImpl
import com.d204.algo.remote.UserRemoteImpl
import com.d204.algo.remote.api.RankingService
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

    @Provides
    @Singleton
    fun provideRankingService(tokenManager: TokenManager): RankingService {
        return ServiceFactory.createRankingService(Constants.DEBUG, Constants.BASE_URL, tokenManager)
    }

    @Provides
    @Singleton
    fun provideUserRemote(characterRemote: UserRemoteImpl): UserRemote {
        return characterRemote
    }

    @Provides
    @Singleton
    fun provideRankingRemote(characterRemote: RankingRemoteImpl): RankingRemote {
        return characterRemote
    }
}
