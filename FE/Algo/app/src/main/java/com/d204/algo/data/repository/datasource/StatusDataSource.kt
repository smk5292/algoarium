package com.d204.algo.data.repository.datasource

import com.d204.algo.data.model.Status

interface StatusDataSource {
    // remote
    suspend fun getStatus(userId: Long): Status

    // cache

    // remote & cache (공통)

    // datasource
    suspend fun isRemote(): Boolean
}
