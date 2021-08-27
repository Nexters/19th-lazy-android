package com.nexters.lazy.util

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class KakaoLoginManager(private val context: Context) {
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            // TODO 로그인 실패 예외 처리
            Timber.e("로그인 실패 error : $error")
        }
        Timber.e("로그인 성공 token : $token")
    }

    // TODO 비동기
    fun requestLogin() = CoroutineScope(Dispatchers.IO).launch{
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }
}