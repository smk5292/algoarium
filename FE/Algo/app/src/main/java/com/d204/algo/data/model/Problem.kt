package com.d204.algo.data.model

import android.os.Parcelable
import com.d204.algo.ui.adapter.Identifiable
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
    val userId: Long,
) : Parcelable, Identifiable {
    constructor() : this(
        id = -1L,
        problemNumber = -1,
        title = "",
        problemTag = "",
        problemLevel = -1,
        solvedUserCount = -1,
        problemLike = false,
        problemMemo = "",
        userId = -1L,
    )

    constructor(problemId: Long, userId: Long, problemMemo: String) : this(
        id = problemId,
        problemNumber = -1,
        title = "",
        problemTag = "",
        problemLevel = -1,
        solvedUserCount = -1,
        problemLike = false,
        problemMemo = problemMemo,
        userId = userId,
    )
}