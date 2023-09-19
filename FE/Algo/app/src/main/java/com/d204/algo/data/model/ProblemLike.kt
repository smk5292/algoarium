package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProblemLike(
    override val id: Int,
    val userId: Int,
    val problemId: Int,
    val likeType: Boolean,
    val memo: String,
) : Parcelable, Identifiable
