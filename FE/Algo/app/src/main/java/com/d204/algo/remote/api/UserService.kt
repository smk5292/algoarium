package com.d204.algo.remote.api

import com.d204.algo.remote.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/user")
    suspend fun getUsers(): List<UserModel>

    @GET("api/user/{tier}")
    suspend fun getUsersByTier(@Path("tier") tier: Int): List<UserModel>

    @GET("api/user/login/{accessToken}/{refreshToken}")
    suspend fun getUser(@Path("accessToken") accessToken: String, @Path("refreshToken") refreshToken: String): UserModel

    @GET("api/user/solvedac")
    suspend fun getSolvedCode(): String
}
