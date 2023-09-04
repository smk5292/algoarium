package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    override val id: Int,
    val activityId: Int,
    val loginId: String,
    var loginPassword: String,
    var phoneNumber: String,
    var email: String,
    var account: String,
    var isGhost: Boolean,
) : Parcelable, Identifiable
