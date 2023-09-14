package com.d204.algo.data.repository.remote

import com.d204.algo.data.model.User


interface UserRemote {
    suspend fun getUsers(): List<User>
    suspend fun getUser(userId: Int): User
    suspend fun isRemote(): Boolean
}