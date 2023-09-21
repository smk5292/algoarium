package com.d204.algo.data.repository

import com.d204.algo.data.model.Status
import kotlinx.coroutines.flow.Flow

interface StatusRepository {
    suspend fun getStatus(userId: Long): Flow<Status>
}
