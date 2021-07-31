package com.nexters.lazy.habitform

import android.os.Bundle
import androidx.activity.viewModels
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseToolbarActivity
import com.nexters.lazy.base.NavType
import com.nexters.lazy.databinding.ActivityHabitDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class HabitFormActivity(
) : BaseToolbarActivity<ActivityHabitDetailBinding, HabitFormViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_habit_detail

    override val viewModelClass: KClass<HabitFormViewModel>
        get() = HabitFormViewModel::class

    private val vieWModel: HabitFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNaviType(NavType.CLOSE)
        setToolbarTitle(R.string.habit_adding)
    }



}