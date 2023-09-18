package com.d204.algo.remote.api

import com.d204.algo.remote.model.ProblemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProblemService {
    @GET("api/problem")
    suspend fun getProblems(): Response<List<ProblemModel>>

    @GET("api/problem/{id}")
    suspend fun getProblem(@Path("id") id: Int): Response<ProblemModel>
}
