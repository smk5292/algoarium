package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    override val id: Long,
    val kakaoId: String,
    val kakaoNickname: String,
    var profileImage: String,
    var preTier: Int,
) : Parcelable, Identifiable {
    constructor() : this(-1, "", "", "", -1)
}
