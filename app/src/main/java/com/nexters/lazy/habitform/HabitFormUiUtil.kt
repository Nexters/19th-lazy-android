package com.nexters.lazy.habitform

import com.nexters.lazy.R

object HabitFormUiUtil {
    fun populateHabitIcons() = listOf<HabitIcon>(
        HabitIcon(id = 1, res = R.drawable.habbit_1),
        HabitIcon(id = 2, res = R.drawable.habbit_2),
        HabitIcon(id = 3, res = R.drawable.habbit_3),
        HabitIcon(id = 4, res = R.drawable.habbit_4),
        HabitIcon(id = 5, res = R.drawable.habbit_5)
    )
}