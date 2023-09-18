package com.d204.algo.remote.mapper

import com.d204.algo.data.model.ProblemLike
import com.d204.algo.remote.model.ProblemLikeModel
import javax.inject.Inject

class ProblemLikeMapper @Inject constructor() : Mapper<ProblemLikeModel, ProblemLike> {
    override fun mapFromModel(model: ProblemLikeModel): ProblemLike {
        return ProblemLike(
            id = model.id,
            userId = model.userId,
            problemId = model.problemId,
            likeType = model.likeType,
            memo = model.memo,
        )
    }

    override fun mapToModel(type: ProblemLike): ProblemLikeModel {
        return ProblemLikeModel(
            id = type.id,
            userId = type.userId,
            problemId = type.problemId,
            likeType = type.likeType,
            memo = type.memo,
        )
    }
}
