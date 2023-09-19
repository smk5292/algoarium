package com.d204.algo.data.repository

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsers(): Flow<NetworkResult<List<User>>>
    suspend fun getUsersByTier(tier: Int): Flow<NetworkResult<List<User>>>
    suspend fun getUser(accessToken: String, refreshToken: String): Flow<NetworkResult<User>>
}
