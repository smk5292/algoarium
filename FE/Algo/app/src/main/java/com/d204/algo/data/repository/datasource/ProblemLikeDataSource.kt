package com.d204.algo.data.repository.datasource

import com.d204.algo.data.model.ProblemLike

interface ProblemLikeDataSource {
    // remote
    suspend fun getProblemLikes(): List<ProblemLike>

    // cache

    // remote & cache

    // datasource
    suspend fun isRemote(): Boolean
}
