package com.d204.algo.data.source.remote

import com.d204.algo.data.model.Ranking
import com.d204.algo.data.repository.datasource.RankingDataSource
import com.d204.algo.data.repository.remote.RankingRemote
import javax.inject.Inject

class RankingRemoteDataSource @Inject constructor(
    private val rankingRemote: RankingRemote,
) : RankingDataSource {
    override suspend fun getRankings(): List<Ranking> {
        return rankingRemote.getRankings()
    }

    override suspend fun getRankingsByTier(tier: Int): List<Ranking> {
        return rankingRemote.getRankingsByTier(tier)
    }

    override suspend fun getRanking(userId: Long): Ranking {
        return rankingRemote.getRanking(userId)
    }

    override suspend fun isRemote(): Boolean {
        return rankingRemote.isRemote()
    }
}
