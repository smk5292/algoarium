package com.d204.algo.data.repository

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.model.Problem
import kotlinx.coroutines.flow.Flow

interface ProblemRepository {
    suspend fun getProblems(): Flow<NetworkResult<List<Problem>>>
    suspend fun getProblem(problemId: Long): Flow<NetworkResult<Problem>>
}
