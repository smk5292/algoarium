package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class StatusModel(
    @field:Json(name = "status_id") val id: Int,
    @field:Json(name = "user_id") val userId: Int,
)
