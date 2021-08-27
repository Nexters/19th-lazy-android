package com.nexters.lazy

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this

        KakaoSdk.init(this, getString(R.string.kakao_api_key))
    }

    companion object{
        lateinit var app : App
    }
}