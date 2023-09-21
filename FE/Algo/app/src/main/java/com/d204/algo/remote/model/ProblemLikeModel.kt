package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class ProblemLikeModel(
    @field:Json(name = "problem_like_id") val id: Long,
    @field:Json(name = "user_id") val userId: Long,
    @field:Json(name = "problem_id") val problemId: Long,
    @field:Json(name = "like_type") val likeType: Boolean,
    @field:Json(name = "memo") val memo: String,
)
