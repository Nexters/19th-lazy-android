package com.nexters.lazy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import com.nexters.lazy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val titles = resources.getStringArray(R.array.countries)
        val colors = resources.obtainTypedArray(R.array.colors)

        viewBinding.picker.adapter = object : BubblePickerAdapter {
            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position]
                        BubbleGradient(
                            colors.getColor(position * 2 % 8, 0),
                            colors.getColor(position * 2 % 8 + 1, 0), BubbleGradient.VERTICAL
                        )
                    textColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
                }
            }
        }
        viewBinding.picker.bubbleSize = 8
        viewBinding.picker.centerImmediately = false

        colors.recycle()

        viewBinding.picker.listener = object : BubblePickerListener {
            override fun onBubbleSelected(item: PickerItem) {

            }

            override fun onBubbleDeselected(item: PickerItem) {
            }
        }
    }


    override fun onResume() {
        super.onResume()
        viewBinding.picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewBinding.picker.onPause()

    }
}