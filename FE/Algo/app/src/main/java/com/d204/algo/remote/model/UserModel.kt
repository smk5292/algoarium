package com.d204.algo.remote.model

import com.squareup.moshi.Json

data class UserModel(
    @field:Json(name = "user_id") val id: Int,
    @field:Json(name = "activity_id") val activityId: Int,
    @field:Json(name = "login_id") val loginId: String,
    @field:Json(name = "login_password") var loginPassword: String,
    @field:Json(name = "phone_number") var phoneNumber: String,
    @field:Json(name = "email") var email: String,
    @field:Json(name = "account_number") var account: String,
    @field:Json(name = "activate") var isGhost: Boolean,
)
