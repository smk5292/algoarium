package com.d204.algo.remote.api

import com.d204.algo.remote.model.RankingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RankingService {
    @GET("api/user_ranking")
    suspend fun getRankings(): Response<List<RankingModel>>

    @GET("api/user_ranking/{tier}")
    suspend fun getRankingsByTier(@Path("tier") tier: Int): Response<List<RankingModel>>

    @GET("api/user_ranking/{id}")
    suspend fun getRanking(@Path("id") id: Long): Response<RankingModel>
}
