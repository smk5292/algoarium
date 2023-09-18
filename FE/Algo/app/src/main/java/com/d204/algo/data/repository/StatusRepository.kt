package com.d204.algo.data.repository

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.model.Status
import kotlinx.coroutines.flow.Flow

interface StatusRepository {
    suspend fun getStatus(userId: Int): Flow<NetworkResult<Status>>
}