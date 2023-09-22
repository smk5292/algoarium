package com.d204.algo.data.repository.datasource

import com.d204.algo.data.model.Problem

interface ProblemDataSource {
    // remote
    suspend fun getProblems(): List<Problem>
    suspend fun getProblem(problemId: Long): Problem
    suspend fun getLikeProblems(userId: Long): List<Problem>

    // cache

    // remote & cache (공통)

    // datasource
    suspend fun isRemote(): Boolean
}
