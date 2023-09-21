package com.d204.algo.data.source.datasource

import com.d204.algo.data.repository.datasource.ProblemLikeDataSource
import com.d204.algo.data.source.cache.ProblemLikeCacheDataSource
import com.d204.algo.data.source.remote.ProblemLikeRemoteDataSource
import javax.inject.Inject

class ProblemLikeDataSourceFactory @Inject constructor(
    private val cacheDataSource: ProblemLikeCacheDataSource,
    private val remoteDataSource: ProblemLikeRemoteDataSource,
) {
    open suspend fun getDataSource(isRemote: Boolean): ProblemLikeDataSource {
        return if (isRemote) {
            getRemoteDataSource()
        } else {
            getCacheDataSource()
        }
    }

    fun getRemoteDataSource(): ProblemLikeRemoteDataSource {
        return remoteDataSource
    }

    fun getCacheDataSource(): ProblemLikeCacheDataSource {
        return cacheDataSource
    }
}
