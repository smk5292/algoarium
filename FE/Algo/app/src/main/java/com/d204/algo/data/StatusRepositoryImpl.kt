package com.d204.algo.data

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.api.handleApi
import com.d204.algo.data.model.Status
import com.d204.algo.data.repository.StatusRepository
import com.d204.algo.data.source.datasource.StatusDataSourceFactory
import com.d204.algo.remote.mapper.StatusMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StatusRepositoryImpl @Inject constructor(
    private val dataSourceFactory: StatusDataSourceFactory,
    private val statusMapper: StatusMapper,
) : StatusRepository {
    override suspend fun getStatus(userId: Long): Flow<NetworkResult<Status>> = flow {
        val isRemote = dataSourceFactory.getRemoteDataSource().isRemote() // 무조건 true가 나오도록 StatusRemoteImpl에 구현돼있음
        emit(handleApi { dataSourceFactory.getDataStore(isRemote).getStatus(userId) })
    }
}
