package com.nexters.lazy

import android.content.Context
import android.util.DisplayMetrics
import android.view.View

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun Int.toPxInt(context: Context): Int {
    val r = context.resources
    val m = r.displayMetrics
    return this * m.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}

fun Double.toPxInt(context: Context): Int {
    val r = context.resources
    val m = r.displayMetrics
    return (this * m.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toInt()
}
