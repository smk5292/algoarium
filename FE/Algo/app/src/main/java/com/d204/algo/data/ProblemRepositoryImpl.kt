package com.d204.algo.data

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.api.handleApi
import com.d204.algo.data.model.Problem
import com.d204.algo.data.repository.ProblemRepository
import com.d204.algo.data.source.datasource.ProblemDataSourceFactory
import com.d204.algo.remote.mapper.ProblemMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProblemRepositoryImpl @Inject constructor(
    private val dataSourceFactory: ProblemDataSourceFactory,
    private val problemMapper: ProblemMapper,
) : ProblemRepository {
    override suspend fun getProblems(): Flow<NetworkResult<List<Problem>>> = flow {
        val isRemote = dataSourceFactory.getRemoteDataSource().isRemote()
        emit(handleApi { dataSourceFactory.getDataSource(isRemote).getProblems() })
    }

    override suspend fun getProblem(problemId: Long): Flow<NetworkResult<Problem>> = flow {
        emit(handleApi { dataSourceFactory.getRemoteDataSource().getProblem(problemId) })
    }

    override suspend fun getLikeProblems(userId: Long): Flow<List<Problem>> = flow {
        val isRemote = dataSourceFactory.getRemoteDataSource().isRemote()
        emit(dataSourceFactory.getDataSource(isRemote).getLikeProblems(userId))
    }
}
