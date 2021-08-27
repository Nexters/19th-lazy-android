package com.nexters.lazy.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseActivity
import com.nexters.lazy.databinding.ActivitySplashBinding
import com.nexters.lazy.ui.home.HomeActivity
import com.nexters.lazy.ui.onaording.FirstOnBoardingActivity
import com.nexters.presentation.viewmodel.NoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, NoViewModel>() {
    override val layoutResId = R.layout.activity_splash
    override val viewModelClass = NoViewModel::class

    private val SPLAH_TIME = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, FirstOnBoardingActivity::class.java))
            finish()
        }, SPLAH_TIME)
    }
}