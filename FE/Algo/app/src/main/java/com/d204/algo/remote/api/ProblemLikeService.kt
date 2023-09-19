package com.d204.algo.remote.api

import com.d204.algo.remote.model.ProblemLikeModel
import retrofit2.Response
import retrofit2.http.GET

interface ProblemLikeService {
    @GET("api/problemLike")
    suspend fun getProblemLikes(): Response<List<ProblemLikeModel>>
}
