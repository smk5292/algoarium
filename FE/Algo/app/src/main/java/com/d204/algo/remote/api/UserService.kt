package com.d204.algo.remote.api

import com.d204.algo.remote.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("user")
    suspend fun getUsers(): Response<List<UserModel>>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserModel>
}
