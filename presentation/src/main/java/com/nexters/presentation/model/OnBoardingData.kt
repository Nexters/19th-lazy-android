package com.nexters.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnBoardingData(
    val order: Int,
    val main: String,
    val sub: String,
    val imageId: Int
) : Parcelable