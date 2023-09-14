package com.d204.algo.data.source.remote

import com.d204.algo.data.model.User
import com.d204.algo.data.repository.datasource.UserDataSource
import com.d204.algo.data.repository.remote.UserRemote
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userRemote: UserRemote
) : UserDataSource {
    override suspend fun getUsers(): List<User> {
        return userRemote.getUsers()
    }

    override suspend fun getUser(userId: Int): User {
        return userRemote.getUser(userId)
    }

    override suspend fun isRemote(): Boolean {
       return userRemote.isRemote()
    }

}