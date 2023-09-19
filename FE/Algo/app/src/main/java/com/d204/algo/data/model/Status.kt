package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Status(
    override val id: Int,
    val userId: Int,
    var wisdom: Int,
    var vitality: Int,
    var strength: Int,
    var charisma: Int,
    var luck: Int
) : Parcelable, Identifiable
