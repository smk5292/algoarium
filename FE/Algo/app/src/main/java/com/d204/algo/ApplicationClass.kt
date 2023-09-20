package com.d204.algo

import android.app.Application
import com.d204.algo.presentation.utils.PresentationPreferencesHelper
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {
    companion object {
        lateinit var preferencesHelper: PresentationPreferencesHelper
        var skinOn = false
    }

    override fun onCreate() {
        super.onCreate()
        preferencesHelper = PresentationPreferencesHelper(this)
        KakaoSdk.init(this, getString(R.string.kakao_key))
    }
}
