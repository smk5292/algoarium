package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class ProblemLikeModel(
    @field:Json(name = "problem_like_id") val id: Int,
    @field:Json(name = "user_id") val userId: Int,
    @field:Json(name = "problem_id") val problemId: Int,
    @field:Json(name = "like_type") val likeType: Boolean,
    @field:Json(name = "memo") val memo: String,
)
