package com.nexters.lazy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.user.UserApiClient
import com.nexters.lazy.MainActivity
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseActivity
import com.nexters.lazy.databinding.ActivityLoginBinding
import com.nexters.lazy.util.KakaoLoginManager
import com.nexters.presentation.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(){
    override val layoutResId = R.layout.activity_login
    override val viewModelClass = LoginViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListener()
    }

    fun initListener(){
        binding.apply {
            tvLogin.setOnClickListener {
                KakaoLoginManager(this@LoginActivity).requestLogin()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}