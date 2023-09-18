package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class UserModel(
    @field:Json(name = "user_id") val id: Int,
    @field:Json(name = "kakao_id") val kakaoId: String,
    @field:Json(name = "kakao_nickname") val kakaoNickname: String,
    @field:Json(name = "profile_image") val profileImage: String,
    @field:Json(name = "pre_tier") val preTier: Int,
)
