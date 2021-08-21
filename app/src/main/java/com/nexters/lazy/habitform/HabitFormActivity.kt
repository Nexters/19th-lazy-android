package com.nexters.lazy.habitform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseToolbarActivity
import com.nexters.lazy.base.NavType
import com.nexters.lazy.databinding.ActivityHabitDetailBinding
import com.nexters.presentation.viewmodel.HabitFormViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class HabitFormActivity(
) : BaseToolbarActivity<ActivityHabitDetailBinding, HabitFormViewModel>() {

    companion object {

        private const val EXTRA_HABIT_TYPE = "EXTRA_HABIT_TYPE"

        fun newIntent(context: Context, type: HabitFormType): Intent {
            return Intent(context, HabitFormActivity::class.java).apply {
                putExtra(EXTRA_HABIT_TYPE, type)
            }
        }
    }

    override val layoutResId: Int
        get() = R.layout.activity_habit_detail

    override val viewModelClass: KClass<HabitFormViewModel>
        get() = HabitFormViewModel::class

    private lateinit var habitIconAdapter: HabitIconAdapter

    private val vieWModel: HabitFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNaviType(NavType.CLOSE)
        setToolbarTitle(R.string.habit_adding)

        observeViewModel()
        setUi()
    }

    private fun observeViewModel() {

    }

    private fun setUi() {

        habitIconAdapter = HabitIconAdapter(HabitFormUiUtil.populateHabitIcons()) {
            vieWModel.onClickHabitIcon(it.id)
        }
        with(binding) {
            rvHabitIcon.adapter = habitIconAdapter
            rvHabitIcon.layoutManager =
                LinearLayoutManager(this@HabitFormActivity, RecyclerView.HORIZONTAL, false)
        }
    }
}