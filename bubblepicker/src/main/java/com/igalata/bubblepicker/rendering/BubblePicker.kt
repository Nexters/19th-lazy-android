package com.igalata.bubblepicker.rendering

import android.content.Context
import android.graphics.PixelFormat
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.ColorInt
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.R
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.Color
import com.igalata.bubblepicker.model.PickerItem

/**
 * Created by irinagalata on 1/19/17.
 */
class BubblePicker : GLSurfaceView {

    @ColorInt
    var background: Int = 0
        set(value) {
            field = value
            renderer.backgroundColor = Color(value)
        }
    @Deprecated(level = DeprecationLevel.WARNING,
            message = "Use BubblePickerAdapter for the view setup instead")
    var items: ArrayList<PickerItem>? = null
        set(value) {
            field = value
            renderer.items = value ?: ArrayList()
        }
    var adapter: BubblePickerAdapter? = null
        set(value) {
            field = value
            if (value != null) {
                renderer.items = ArrayList((0..value.totalCount - 1)
                        .map { value.getItem(it) }.toList())
            }
        }
    var maxSelectedCount: Int? = null
        set(value) {
            renderer.maxSelectedCount = value
            field = value
        }

    var listener: BubblePickerListener? = null
        set(value) {
            renderer.listener = value
            field = value
        }

    var bubbleSize = 50
        set(value) {
            if (value in 1..100) {
                renderer.bubbleSize = value
            }
            field = value
        }

    private val renderer = PickerRenderer(this)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setZOrderOnTop(true)
        setEGLContextClientVersion(2)
        setEGLConfigChooser(8, 8, 8, 8, 16, 0)
        holder.setFormat(PixelFormat.RGBA_8888)
        setRenderer(renderer)
        renderMode = RENDERMODE_CONTINUOUSLY
        attrs?.let { retrieveAttrubutes(attrs) }
    }

    private fun retrieveAttrubutes(attrs: AttributeSet) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.BubblePicker)

        if (array.hasValue(R.styleable.BubblePicker_maxSelectedCount)) {
            maxSelectedCount = array.getInt(R.styleable.BubblePicker_maxSelectedCount, -1)
        }

        if (array.hasValue(R.styleable.BubblePicker_backgroundColor)) {
            background = array.getColor(R.styleable.BubblePicker_backgroundColor, -1)
        }
        array.recycle()
    }

    fun clear(){
        postDelayed({ renderer.initialize() }, 0)
    }
}
