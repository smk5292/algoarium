package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class ProblemModel(
    @field:Json(name = "problem_id") val id: Int,
    @field:Json(name = "problem_number") val problemNumber: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "problem_tag") val problemTag: String,
    @field:Json(name = "problem_level") val problemLevel: Int,
    @field:Json(name = "solved_user_count") val solvedUserCount: Int,
)
