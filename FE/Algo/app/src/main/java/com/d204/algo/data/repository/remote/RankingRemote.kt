package com.d204.algo.data.repository.remote

import com.d204.algo.data.model.Ranking

interface RankingRemote {
    suspend fun getRankings(): List<Ranking>
    suspend fun getRankingsByTier(tier: Int): List<Ranking>
    suspend fun getRanking(userId: Long): Ranking
    suspend fun isRemote(): Boolean
}