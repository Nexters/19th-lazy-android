package com.nexters.lazy.ui.onaording

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseActivity
import com.nexters.lazy.databinding.ActivityOnboarding2Binding
import com.nexters.lazy.ui.LoginActivity
import com.nexters.lazy.ui.home.HomeActivity
import com.nexters.presentation.model.OnBoardingData
import com.nexters.presentation.viewmodel.NoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondOnBoardingActivity : BaseActivity<ActivityOnboarding2Binding, NoViewModel>() {
    override val layoutResId = R.layout.activity_onboarding2
    override val viewModelClass = NoViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }

    fun initView(){
        binding.viewpager.adapter = OnBoardingAdapter2(this)
        TabLayoutMediator(binding.indicator, binding.viewpager){ tab, position ->

        }.attach()
    }

    fun initListener(){
        with(binding){
            tvDone.setOnClickListener {
                if(viewpager.currentItem == FirstOnBoardingActivity.PAGE_NUM -1){
                    // TODO set shared Preference
                    startActivity(Intent(this@SecondOnBoardingActivity, HomeActivity::class.java))
                    finish()
                }
            }
            viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if(position == FirstOnBoardingActivity.PAGE_NUM -1) tvDone.visibility = View.VISIBLE
                }
            })
        }
    }

    private inner class OnBoardingAdapter2(fa : FragmentActivity) : FragmentStateAdapter(fa){
        val list = arrayListOf(
            OnBoardingData(1, getString(R.string.main2_1), getString(R.string.sub2_1), R.drawable.onboarding2_1),
            OnBoardingData(2, getString(R.string.main2_2), getString(R.string.sub2_2), R.drawable.onboarding2_2),
            OnBoardingData(3, getString(R.string.main2_3), getString(R.string.sub2_3), R.drawable.onboarding2_3)
        )
        override fun getItemCount() = PAGE_NUM
        override fun createFragment(position: Int) = SecondOnBoardingFragment.getInstance(list[position])
    }

    companion object{
        const val PAGE_NUM = 3
    }
}