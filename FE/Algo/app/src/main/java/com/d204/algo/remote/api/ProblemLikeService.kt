package com.d204.algo.remote.api

import com.d204.algo.remote.model.ProblemLikeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProblemLikeService {
    @GET("my/like/{userId}")
    suspend fun getProblemLikes(@Path("userId") userId: Int): Response<List<ProblemLikeModel>>
}
