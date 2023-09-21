package com.d204.algo.data.source.remote

import com.d204.algo.data.model.ProblemLike
import com.d204.algo.data.repository.datasource.ProblemLikeDataSource
import com.d204.algo.data.repository.remote.ProblemLikeRemote
import javax.inject.Inject

class ProblemLikeRemoteDataSource @Inject constructor(
    private val problemLikeRemote: ProblemLikeRemote
) : ProblemLikeDataSource {
    override suspend fun getProblemLikes(): List<ProblemLike> {
        return problemLikeRemote.getProblemLikes()
    }

    override suspend fun isRemote(): Boolean {
        return problemLikeRemote.isRemote()
    }
}
