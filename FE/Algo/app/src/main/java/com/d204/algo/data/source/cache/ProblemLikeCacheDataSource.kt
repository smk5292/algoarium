package com.d204.algo.data.source.cache

import com.d204.algo.data.model.ProblemLike
import com.d204.algo.data.repository.cache.ProblemLikeCache
import com.d204.algo.data.repository.datasource.ProblemLikeDataSource
import javax.inject.Inject

class ProblemLikeCacheDataSource @Inject constructor(
    private val problemLikeCache: ProblemLikeCache
) : ProblemLikeDataSource {
    override suspend fun getProblemLikes(): List<ProblemLike> {
        throw UnsupportedOperationException("getProblemLikes is not supported for ProblemLikeCacheDataSource.")
    }

    override suspend fun isRemote(): Boolean {
        throw UnsupportedOperationException("getProblemLikes is not supported for ProblemLikeCacheDataSource.")
    }
}
