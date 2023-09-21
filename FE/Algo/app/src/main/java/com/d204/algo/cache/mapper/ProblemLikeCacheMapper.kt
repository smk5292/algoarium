package com.d204.algo.cache.mapper

import com.d204.algo.data.model.Problem
import javax.inject.Inject

class ProblemLikeCacheMapper @Inject constructor() : CacheMapper<ProblemLikeCacheMapper, Problem> {
    override fun mapFromCached(type: ProblemLikeCacheMapper): Problem {
        TODO("Not yet implemented")
    }

    override fun mapToCached(type: Problem): ProblemLikeCacheMapper {
        TODO("Not yet implemented")
    }
}
