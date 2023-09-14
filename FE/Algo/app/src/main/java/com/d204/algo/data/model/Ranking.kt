package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ranking(
    override val id: Int,
    val userId: Int,
    var score: Int,
    val ranking: Int,
    val tier: Int
) : Parcelable, Identifiable
