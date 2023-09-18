package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ranking(
    override val id: Int,
    var userId: Int,
    var score: Int,
    var tier: Int,
    var ranking: Int,
) : Parcelable, Identifiable
