package com.d204.algo.data

import com.d204.algo.data.api.NetworkResult
import com.d204.algo.data.api.handleApi
import com.d204.algo.data.model.User
import com.d204.algo.data.repository.UserRepository
import com.d204.algo.data.source.datasource.UserDataSourceFactory
import com.d204.algo.remote.mapper.UserMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSourceFactory: UserDataSourceFactory,
    private val userMapper: UserMapper,
) : UserRepository {
    // local , remote 모두에서 가져올 수 있는 경우 isRemote를 이용해서 어디서 가져올 것인지 사용자에게 입력받는다.
    // remote return 타입 Response 이므로 body() 붙여야함
    // 로컬에서 올 수 있는데 Response를 어떻게 분기 처리할까? 
    // Service는 Response로 return <> Repository는 NetworkResult<Response> return
    override suspend fun getUsers(): Flow<NetworkResult<List<User>>> = flow {
        val isRemote = dataSourceFactory.getRemoteDataSource().isRemote() // 무조건 true가 나오도록 UserRemoteImpl에 구현돼있음
        emit(handleApi { dataSourceFactory.getDataStore(isRemote).getUsers() })
    }

    override suspend fun getUser(userId: Int): Flow<NetworkResult<User>> = flow {
        emit(handleApi { dataSourceFactory.getRemoteDataSource().getUser(userId) })
    }

}