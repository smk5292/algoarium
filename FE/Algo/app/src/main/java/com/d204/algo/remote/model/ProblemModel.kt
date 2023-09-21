package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class ProblemModel(
    @Json(name = "problem_id") val id: Long,
    @Json(name = "problem_number") val problemNumber: Int,
    @Json(name = "title") val title: String,
    @Json(name = "problem_tag") val problemTag: String,
    @Json(name = "problem_level") val problemLevel: Int,
    @Json(name = "solved_user_count") val solvedUserCount: Int,
    @Json(name = "like_type") val problemLike: Boolean,
    @Json(name = "memo") val problemMemo: String,
)
