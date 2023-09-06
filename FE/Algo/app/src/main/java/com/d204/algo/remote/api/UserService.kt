package com.d204.algo.remote.api

import com.d204.algo.remote.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/user")
    suspend fun getUsers(): Response<List<UserModel>>

    @GET("api/user/{tier}")
    suspend fun getUsersByTier(@Path("tier") tier: Int): Response<List<UserModel>>

    @GET("api/user/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserModel>
}
