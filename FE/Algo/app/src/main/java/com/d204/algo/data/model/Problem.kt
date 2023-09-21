package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Problem(
    override val id: Long,
    val problemNumber: Int,
    val title: String,
    val problemTag: String,
    val problemLevel: Int,
    val solvedUserCount: Int,
    val problemLike: Boolean,
    val problemMemo: String,
) : Parcelable, Identifiable
