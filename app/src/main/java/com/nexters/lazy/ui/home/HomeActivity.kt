package com.nexters.lazy.ui.home

import android.os.Bundle
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseActivity
import com.nexters.lazy.databinding.ActivityHomeBinding
import com.nexters.presentation.viewmodel.MainViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, MainViewModel>(){
    override val layoutResId = R.layout.activity_home
    override val viewModelClass = MainViewModel::class

    private var currentFragment = HomeFragment.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }

    fun initView(){
        supportFragmentManager.beginTransaction().add(binding.containerView.id, currentFragment).commit()
    }

    fun initListener(){
        binding.containerTab.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.page_home -> {
                    currentFragment = HomeFragment.getInstance()
                }
            }
            supportFragmentManager.beginTransaction().replace(binding.containerView.id, currentFragment)
            true
        }
    }
}