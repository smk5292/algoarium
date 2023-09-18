package com.d204.algo.data.repository.remote

import com.d204.algo.data.model.ProblemLike

interface ProblemLikeRemote {
    suspend fun getProblemLikes(): List<ProblemLike>
    suspend fun isRemote(): Boolean
}
