package com.d204.algo.data.repository.remote

import com.d204.algo.data.model.Problem

interface ProblemRemote {
    suspend fun getProblems(): List<Problem>
    suspend fun getProblem(problemId: Long): Problem
    suspend fun isRemote(): Boolean
}
