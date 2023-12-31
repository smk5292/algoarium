package com.d204.algo.presentation.utils

import android.util.Log
import com.kakao.sdk.common.Constants.AUTHORIZATION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
) : Interceptor {
    // 임시 생성 코드
    companion object {
        const val NETWORK_ERROR = 401
        const val TAG = "AuthInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = runBlocking {
            tokenManager.getAccessToken().first()
        } ?: return errorResponse(chain.request())

        val request = chain.request().newBuilder().header(AUTHORIZATION, "Bearer $token").build()

        val response = chain.proceed(request)
        if (response.code == HTTP_OK) {
            val newAccessToken: String = response.header(AUTHORIZATION, null) ?: return response
            Log.d(TAG, "new Access Token = $newAccessToken")

            CoroutineScope(Dispatchers.IO).launch {
                val existedAccessToken = tokenManager.getAccessToken().first()
                if (existedAccessToken != newAccessToken) {
                    tokenManager.saveAccessToken(newAccessToken)
                    Log.d(TAG, "newAccessToken = ${newAccessToken}\nExistedAccessToken = $existedAccessToken")
                }
            }
        } else {
            Log.e(TAG, "${response.code} : ${response.request} \n ${response.message}")
        }

        return response
    }

    private fun errorResponse(request: Request): Response = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_2)
        .code(NETWORK_ERROR)
        .message("")
        .body(ResponseBody.create(null, ""))
        .build()
}
