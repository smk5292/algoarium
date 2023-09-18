package com.d204.algo.remote.mapper

import com.d204.algo.data.model.Status
import com.d204.algo.remote.model.StatusModel
import javax.inject.Inject

class StatusMapper @Inject constructor() : Mapper<StatusModel, Status> {
    override fun mapFromModel(model: StatusModel): Status {
        return Status(
            id = model.id,
            userId = model.userId
        )
    }

    override fun mapToModel(type: Status): StatusModel {
        return StatusModel(
            id = type.id,
            userId = type.userId
        )
    }
}
