package com.d204.algo.data.repository

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.model.Ranking
import kotlinx.coroutines.flow.Flow

interface RankingRepository {
    suspend fun getRankings(): Flow<NetworkResult<List<Ranking>>>
    suspend fun getRankingsByTier(tier: Int): Flow<NetworkResult<List<Ranking>>>
    suspend fun getRanking(userId: Long): Flow<NetworkResult<Ranking>>
}