package com.d204.algo.remote

import com.d204.algo.data.model.Problem
import com.d204.algo.data.repository.remote.ProblemRemote
import com.d204.algo.remote.api.ProblemService
import com.d204.algo.remote.mapper.ProblemMapper
import javax.inject.Inject

class ProblemRemoteImpl @Inject constructor(
    private val problemService: ProblemService,
    private val problemMapper: ProblemMapper,
) : ProblemRemote {
    override suspend fun getProblems(): List<Problem> {
        return problemService.getProblems().map { problemModel ->
            problemMapper.mapFromModel(problemModel)
        }
    }

    override suspend fun getProblem(problemId: Long): Problem {
        return problemMapper.mapFromModel(problemService.getProblem(problemId))
    }

    override suspend fun isRemote(): Boolean {
        return true
    }
}
