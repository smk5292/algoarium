package com.d204.algo.remote.mapper

import com.d204.algo.data.model.User
import com.d204.algo.remote.model.UserModel
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserModel, User>  {
    override fun mapFromModel(model: UserModel): User {
        return User(
            id = model.id,
            activityId = model.activityId,
            loginId = model.loginId,
            loginPassword = model.loginPassword,
            phoneNumber = model.phoneNumber,
            email = model.email,
            account = model.account,
            isGhost = model.isGhost
        )
    }

    override fun mapToModel(type: User): UserModel {
        return UserModel(
            id = type.id,
            activityId = type.activityId,
            loginId = type.loginId,
            loginPassword = type.loginPassword,
            phoneNumber = type.phoneNumber,
            email = type.email,
            account = type.account,
            isGhost = type.isGhost
        )
    }
}