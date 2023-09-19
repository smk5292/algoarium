package com.d204.algo.data.repository

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.model.ProblemLike
import kotlinx.coroutines.flow.Flow

interface ProblemLikeRepository {
    suspend fun getProblemLikes(): Flow<NetworkResult<List<ProblemLike>>>
}
