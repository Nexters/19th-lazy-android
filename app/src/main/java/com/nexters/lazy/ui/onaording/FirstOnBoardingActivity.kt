package com.nexters.lazy.ui.onaording

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseActivity
import com.nexters.lazy.databinding.ActivityOnboarding1Binding
import com.nexters.lazy.ui.LoginActivity
import com.nexters.presentation.model.OnBoardingData
import com.nexters.presentation.viewmodel.NoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstOnBoardingActivity : BaseActivity<ActivityOnboarding1Binding, NoViewModel>() {
    override val layoutResId = R.layout.activity_onboarding1
    override val viewModelClass = NoViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }

    fun initView(){
        binding.viewpager.adapter = OnBoardingAdapter(this)
    }

    fun initListener(){
        with(binding){
            btnNext.setOnClickListener {
                if(viewpager.currentItem == PAGE_NUM-1){
                    // TODO set shared Preference
                    startActivity(Intent(this@FirstOnBoardingActivity, LoginActivity::class.java))
                }
                viewpager.currentItem++
            }
            viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if(position == PAGE_NUM-1) btnNext.text = getString(R.string.btn_start)
                }
            })
        }
    }

    private inner class OnBoardingAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){
        val list = arrayListOf(
            OnBoardingData(1, getString(R.string.main1), getString(R.string.sub1), R.drawable.ic_onboarding1_1),
            OnBoardingData(2, getString(R.string.main2), getString(R.string.sub2), R.drawable.ic_onboarding1_2),
            OnBoardingData(3, getString(R.string.main3), getString(R.string.sub3), R.drawable.ic_onboarding1_3)
        )
        override fun getItemCount() = PAGE_NUM
        override fun createFragment(position: Int) = FirstOnBoardingFragment.getInstance(list[position])
    }

    companion object{
        const val PAGE_NUM = 3
    }
}