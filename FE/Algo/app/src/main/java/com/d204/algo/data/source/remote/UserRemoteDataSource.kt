package com.d204.algo.data.source.remote

import com.d204.algo.data.api.successOr
import com.d204.algo.data.model.User
import com.d204.algo.data.repository.datasource.UserDataSource
import com.d204.algo.data.repository.remote.UserRemote
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userRemote: UserRemote,
) : UserDataSource {
    override suspend fun getUsers(): List<User> {
        return userRemote.getUsers().successOr(emptyList())
    }

    override suspend fun getUsersByTier(tier: Int): List<User> {
        return userRemote.getUsersByTier(tier).successOr(emptyList())
    }

    override suspend fun getUser(accessToken: String, refreshToken: String): User {
        return userRemote.getUser(accessToken, refreshToken).successOr(User())
    }

    override suspend fun registerSolvedAc(userId: Long, code: String): String {
        return userRemote.registerSolvedAc(userId, code).successOr("")
    }

    override suspend fun getIsSeason(): Boolean? {
        return userRemote.getIsSeason().successOr(null)
    }

    override suspend fun isRemote(): Boolean {
        return userRemote.isRemote()
    }
}
