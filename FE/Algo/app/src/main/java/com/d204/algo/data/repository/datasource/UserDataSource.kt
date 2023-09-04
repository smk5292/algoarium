package com.d204.algo.data.repository.datasource

import com.d204.algo.data.model.User


interface UserDataSource {
    // remote
    suspend fun getUsers(): List<User>
    suspend fun getUser(userId: Int): User

    // cache

    // remote & cache (공통)

    // datasource
    suspend fun isRemote(): Boolean
}