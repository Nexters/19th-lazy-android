package com.nexters.lazy.ui

import android.content.Intent
import android.os.Bundle
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseActivity
import com.nexters.lazy.databinding.ActivityJoinBinding
import com.nexters.lazy.ui.onaording.SecondOnBoardingActivity
import com.nexters.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinActivity : BaseActivity<ActivityJoinBinding, JoinViewModel>() {
    override val layoutResId = R.layout.activity_join
    override val viewModelClass = JoinViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    fun initListener(){
        binding.btnDone.setOnClickListener {
            startActivity(Intent(this, SecondOnBoardingActivity::class.java))
            finish()
        }
    }

}