package com.d204.algo.remote

import com.d204.algo.data.model.Ranking
import com.d204.algo.data.repository.remote.RankingRemote
import com.d204.algo.remote.api.RankingService
import com.d204.algo.remote.mapper.RankingMapper
import javax.inject.Inject

class RankingRemoteImpl @Inject constructor(
    private val rankingService: RankingService,
    private val rankingMapper: RankingMapper,
) : RankingRemote {
    override suspend fun getRankings(): List<Ranking> {
        return rankingService.getRankings().body()!!.map { rankingModel ->
            rankingMapper.mapFromModel(rankingModel)
        }
    }

    override suspend fun getRankingsByTier(tier: Int): List<Ranking> {
        return rankingService.getRankingsByTier(tier).body()!!.map { rankingModel ->
            rankingMapper.mapFromModel(rankingModel)
        }
    }

    override suspend fun getRanking(userId: Int): Ranking {
        return rankingMapper.mapFromModel(rankingService.getRanking(userId).body()!!)
    }

    override suspend fun isRemote(): Boolean {
        return true
    }
}
