package com.d204.algo.remote.api

import com.d204.algo.remote.model.StatusModel
import retrofit2.http.GET
import retrofit2.http.Path

interface StatusService {
    @GET("my/stat/{userId}")
    suspend fun getStatus(@Path("userId") userId: Long): StatusModel
}
