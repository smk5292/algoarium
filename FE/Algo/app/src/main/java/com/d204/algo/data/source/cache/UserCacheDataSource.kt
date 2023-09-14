package com.d204.algo.data.source.cache

import com.d204.algo.data.model.User
import com.d204.algo.data.repository.cache.UserCache
import com.d204.algo.data.repository.datasource.UserDataSource
import javax.inject.Inject

class UserCacheDataSource @Inject constructor(
    private val userCache: UserCache
) : UserDataSource {
    override suspend fun getUsers(): List<User> {
        throw UnsupportedOperationException("getUsers is not supported for UserCacheDataSource.")
    }

    override suspend fun getUser(userId: Int): User {
        throw UnsupportedOperationException("getUser is not supported for UserCacheDataSource.")
    }

    override suspend fun isRemote(): Boolean {
        throw UnsupportedOperationException("isRemote is not supported for UserCacheDataSource.")
    }

}