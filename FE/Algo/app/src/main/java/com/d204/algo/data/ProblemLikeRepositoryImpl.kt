package com.d204.algo.data

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.api.handleApi
import com.d204.algo.data.model.ProblemLike
import com.d204.algo.data.repository.ProblemLikeRepository
import com.d204.algo.data.source.datasource.ProblemLikeDataSourceFactory
import com.d204.algo.remote.mapper.ProblemLikeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProblemLikeRepositoryImpl @Inject constructor(
    private val dataSourceFactory: ProblemLikeDataSourceFactory,
    private val problemLikeMapper: ProblemLikeMapper,
) : ProblemLikeRepository {
    override suspend fun getProblemLikes(): Flow<NetworkResult<List<ProblemLike>>> = flow {
        val isRemote = dataSourceFactory.getRemoteDataSource().isRemote()
        emit(handleApi { dataSourceFactory.getDataSource(isRemote).getProblemLikes() })
    }
}
