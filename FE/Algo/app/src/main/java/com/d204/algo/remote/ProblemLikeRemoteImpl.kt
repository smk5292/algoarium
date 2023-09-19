package com.d204.algo.remote

import com.d204.algo.data.model.ProblemLike
import com.d204.algo.data.repository.remote.ProblemLikeRemote
import com.d204.algo.remote.api.ProblemLikeService
import com.d204.algo.remote.mapper.ProblemLikeMapper
import javax.inject.Inject

class ProblemLikeRemoteImpl @Inject constructor(
    private val problemLikeService: ProblemLikeService,
    private val problemLikeMapper: ProblemLikeMapper,
) : ProblemLikeRemote {
    override suspend fun getProblemLikes(): List<ProblemLike> {
        return problemLikeService.getProblemLikes().body()!!.map { problemLikeModel ->
            problemLikeMapper.mapFromModel(problemLikeModel)
        }
    }

    override suspend fun isRemote(): Boolean {
        return true
    }
}
