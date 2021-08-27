package com.nexters.lazy.ui.onaording

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseFragment
import com.nexters.lazy.databinding.FragmentOnboarding1Binding
import com.nexters.presentation.model.OnBoardingData
import com.nexters.presentation.viewmodel.NoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstOnBoardingFragment : BaseFragment<FragmentOnboarding1Binding, NoViewModel>(){
    override val layoutResId = R.layout.fragment_onboarding1
    override val viewModelClass = NoViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){
        requireArguments().getParcelable<OnBoardingData>(BUNDLE_KEY)?.let {
            with(binding){
                tvMain.text = it.main
                tvSub.text = it.sub
                ivImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), it.imageId))
            }
        }
    }

    companion object{
        private const val BUNDLE_KEY = "item"
        fun getInstance(item : OnBoardingData) : FirstOnBoardingFragment{
            val fragment = FirstOnBoardingFragment()
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_KEY, item)
            fragment.arguments= bundle
            return fragment

        }
    }
}