package com.d204.algo.data.source.remote

import com.d204.algo.data.api.successOr
import com.d204.algo.data.model.Problem
import com.d204.algo.data.repository.datasource.ProblemDataSource
import com.d204.algo.data.repository.remote.ProblemRemote
import javax.inject.Inject

class ProblemRemoteDataSource @Inject constructor(
    private val problemRemote: ProblemRemote,
) : ProblemDataSource {
    override suspend fun getProblems(): List<Problem> {
        return problemRemote.getProblems().successOr(emptyList())
    }

    override suspend fun getProblem(problemId: Long): Problem {
        return problemRemote.getProblem(problemId).successOr(Problem())
    }

    override suspend fun getStrongProblems(userId: Long): List<Problem> {
        return problemRemote.getStrongProblems(userId).successOr(emptyList())
    }

    override suspend fun getWeakProblems(userId: Long): List<Problem> {
        return problemRemote.getWeakProblems(userId).successOr(emptyList())
    }

    override suspend fun getSimilarProblems(userId: Long): List<Problem> {
        return problemRemote.getSimilarProblems(userId).successOr(emptyList())
    }

    override suspend fun postLikeProblems(problem: Problem) {
        return problemRemote.postLikeProblems(problem).successOr(Unit)
    }

    override suspend fun isRemote(): Boolean {
        return problemRemote.isRemote()
    }
}
