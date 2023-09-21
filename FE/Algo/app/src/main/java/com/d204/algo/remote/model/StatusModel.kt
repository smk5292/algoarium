package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class StatusModel(
    @field:Json(name = "status_id") val id: Int,
    @field:Json(name = "user_id") val userId: Int,
    @field:Json(name = "wisdom") val wisdom: Int,
    @field:Json(name = "vitality") val vitality: Int,
    @field:Json(name = "strength") val strength: Int,
    @field:Json(name = "charisma") val charisma: Int,
    @field:Json(name = "luck") val luck: Int,
)
