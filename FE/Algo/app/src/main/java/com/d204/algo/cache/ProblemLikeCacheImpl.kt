package com.d204.algo.cache

import com.d204.algo.cache.dao.ProblemLikeDao
import com.d204.algo.cache.mapper.ProblemLikeCacheMapper
import com.d204.algo.data.repository.cache.ProblemLikeCache
import javax.inject.Inject

class ProblemLikeCacheImpl @Inject constructor(
    private val problemLikeDao: ProblemLikeDao,
    private val problemLikeMapper: ProblemLikeCacheMapper,
) : ProblemLikeCache
