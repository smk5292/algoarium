package com.d204.algo.data.source.remote

import com.d204.algo.data.model.Problem
import com.d204.algo.data.repository.datasource.ProblemDataSource
import com.d204.algo.data.repository.remote.ProblemRemote
import javax.inject.Inject

class ProblemRemoteDataSource @Inject constructor(
    private val problemRemote: ProblemRemote,
) : ProblemDataSource {
    override suspend fun getProblems(): List<Problem> {
        return problemRemote.getProblems()
    }

    override suspend fun getProblem(problemId: Long): Problem {
        return problemRemote.getProblem(problemId)
    }

    override suspend fun isRemote(): Boolean {
        return problemRemote.isRemote()
    }
}
