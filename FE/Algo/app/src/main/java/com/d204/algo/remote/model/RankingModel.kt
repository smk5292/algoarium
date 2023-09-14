package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class RankingModel(
    @field:Json(name = "user_ranking_id") val id: Int,
    @field:Json(name = "user_id") val userId: Int,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "ranking") var ranking: Int,
    @field:Json(name = "tier") var tier: Int,
)
