package com.d204.algo.presentation.utils

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent

private const val LOGIN = "login"
private const val PASSCODE = "passcode"
private const val TAG = "StompHandler"

class StompHandler {
    private val mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, Constants.SOCKET_URL)
    private var compositeDisposable: CompositeDisposable? = null
    private val mRestPingDisposable: Disposable? = null

    init {
        resetSubscriptions()
    }

    fun connectStomp(currentChannelId: String) {
        // 소켓에 라이프 사이클 이벤트 등록, Stomp 소켓 연결 여부(클라이언트 단)를 확인하여 에러처리
        val dispLifecycle = mStompClient.lifecycle().subscribe { lifeCycleEvent ->
            when (lifeCycleEvent.type) {
                LifecycleEvent.Type.OPENED -> {
                    Log.d(TAG, "Stomp connection opened")
                }

                LifecycleEvent.Type.ERROR -> {
                    Log.e(TAG, "Stomp connection error", lifeCycleEvent.exception)
                }

                LifecycleEvent.Type.CLOSED -> {
                    Log.d(TAG, "Stomp connection closed")
                    resetSubscriptions()
                }

                LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> {
                    Log.d(TAG, "Stomp failed server heartbeat")
                }
            }
        }

        // 메시지 수신부 (모바일 클라이언트에서는 사용되지 않는 코드 입니다.(Android는 sender역할만 함 & PC 앱은 Send 기능이 없어서 동작안함))
        val dispTopic = mStompClient
            .topic("/app/topic/url/$currentChannelId")
            .subscribeOn(Schedulers.io()) // 피구독자가 데이터 흐름을 어느 스레드에 발생시킬지
            .observeOn(AndroidSchedulers.mainThread()) // 피구독자가 구독자에게 알림을 보낼 때 어느 스레드를 사용할 지
            .subscribe { topicMessage ->
                Log.d("dddddddddd", "connectStomp: $topicMessage")
                val jsonPayload = topicMessage.payload
                val jsonObject = JSONObject(jsonPayload)

                val url = jsonObject.getString("url")
                Log.d(TAG, "connectStomp: $url")
            }

        // Disposable add
        compositeDisposable?.add(dispLifecycle)
        compositeDisposable?.add(dispTopic)
        // 연결
        mStompClient.connect()
    }

    fun sendStomp(message: String, currentChannelId: String) {
        // val writer = ApplicationClass.sharedPreferences.getString("userEmail")!!

        val data = JSONObject()
        data.put("url", message)

        // 메시지를 보낼 엔드포인트 URL
        val messageSendEndpoint = "/app/send/url/$currentChannelId"

        // 전송부
        compositeDisposable?.add(
            mStompClient.send(messageSendEndpoint, data.toString()).subscribe(
                {
                    // 성공시
                    Log.d("Message Sent", "Message sent successfully")
                },
                { error ->
                    // 실패시
                    Log.d("Message Send Error", error.toString())
                },
            ),
        )
    }

    private fun resetSubscriptions() {
        compositeDisposable?.dispose()
        compositeDisposable = CompositeDisposable()
    }

    fun disconnectStomp() {
        mStompClient.disconnect()
    }

    fun destroyStompHandler() {
        mStompClient.disconnect()
        mRestPingDisposable?.dispose()
        if (compositeDisposable != null) compositeDisposable!!.dispose()
    }
}
