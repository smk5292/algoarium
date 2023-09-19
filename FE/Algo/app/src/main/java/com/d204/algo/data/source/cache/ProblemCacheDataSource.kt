package com.d204.algo.data.source.cache

import com.d204.algo.data.model.Problem
import com.d204.algo.data.repository.cache.ProblemCache
import com.d204.algo.data.repository.datasource.ProblemDataSource
import javax.inject.Inject

class ProblemCacheDataSource @Inject constructor(
    private val problemCache: ProblemCache
) : ProblemDataSource {
    override suspend fun getProblems(): List<Problem> {
        throw UnsupportedOperationException("getProblems is not supported for ProblemCacheDataSource.")
    }

    override suspend fun getProblem(problemId: Int): Problem {
        throw UnsupportedOperationException("getProblem is not supported for ProblemCacheDataSource.")
    }

    override suspend fun isRemote(): Boolean {
        throw UnsupportedOperationException("isRemote is not supported for ProblemCacheDataSource.")
    }
}
