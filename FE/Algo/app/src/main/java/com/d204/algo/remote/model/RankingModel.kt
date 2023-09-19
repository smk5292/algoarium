package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class RankingModel(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "user_id") val userId: Int,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "tier") val tier: Int,
    @field:Json(name = "ranking") val ranking: Int,
)
