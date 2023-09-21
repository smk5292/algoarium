package com.d204.algo.remote

import com.d204.algo.data.model.Status
import com.d204.algo.data.repository.remote.StatusRemote
import com.d204.algo.remote.api.StatusService
import com.d204.algo.remote.mapper.StatusMapper
import javax.inject.Inject

class StatusRemoteImpl @Inject constructor(
    private val StatusService: StatusService,
    private val StatusMapper: StatusMapper,
) : StatusRemote {
    override suspend fun getStatus(userId: Long): Status {
        return StatusMapper.mapFromModel(StatusService.getStatus(userId).body()!!)
    }

    // 이 부분 원래는 로컬 DB에 저장된 값이 없으면 true를 호출하게 끔 StatusDao를 이용해야함. 현재는 DataSource에서 받아서 사용
    override suspend fun isRemote(): Boolean {
        return true
    }
}
