package com.d204.algo.remote

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.api.handleApi
import com.d204.algo.data.model.Status
import com.d204.algo.data.repository.remote.StatusRemote
import com.d204.algo.remote.api.StatusService
import com.d204.algo.remote.mapper.StatusMapper
import com.d204.algo.remote.model.ProblemModel
import javax.inject.Inject

class StatusRemoteImpl @Inject constructor(
    private val statusService: StatusService,
    private val statusMapper: StatusMapper,
) : StatusRemote {
    override suspend fun getStatus(userId: Long): NetworkResult<Status> {
        return handleApi {
            statusMapper.mapFromModel(statusService.getStatus(userId))
        }
    }

    override suspend fun updateMemo(problemModel: ProblemModel): NetworkResult<Unit> {
        return handleApi {
            statusService.updateMemo(problemModel)
        }
    }

    // 이 부분 원래는 로컬 DB에 저장된 값이 없으면 true를 호출하게 끔 StatusDao를 이용해야함. 현재는 DataSource에서 받아서 사용
    override suspend fun isRemote(): Boolean {
        return true
    }
}
