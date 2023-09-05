package com.d204.algo.remote.api

import com.d204.algo.presentation.utils.AuthAuthenticator
import com.d204.algo.presentation.utils.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

// RemoteModule에서 사용합니다.
object ServiceFactory {
    @Inject
    lateinit var authInterceptor: AuthInterceptor

    @Inject
    lateinit var authAuthenticator: AuthAuthenticator

    // 각 서비스 별로 create 함수를 별도로 만든다.
    fun createUserService(isDebug: Boolean, baseUrl: String): UserService {
        val retrofit = createRetrofit(isDebug, baseUrl)
        return retrofit.create(UserService::class.java)
    }

    private fun createRetrofit(isDebug: Boolean, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(createLoggingInterceptor(isDebug)))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .authenticator(authAuthenticator)
            .addNetworkInterceptor(authInterceptor)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private const val OK_HTTP_TIMEOUT = 30L
}
