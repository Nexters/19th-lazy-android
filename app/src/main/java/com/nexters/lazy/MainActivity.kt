package com.nexters.lazy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        viewBinding.picker.adapter = object : BubblePickerAdapter {
            override val totalCount = 15

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    gradient = when(position%3){
                        0->
                            BubbleGradient(
                                getColor(R.color.color_main),
                                getColor(R.color.color_main), BubbleGradient.VERTICAL
                            )
                        1->
                            BubbleGradient(
                                getColor(R.color.color_main),
                                getColor(R.color.color_main), BubbleGradient.VERTICAL
                            )
                        2->
                            BubbleGradient(
                                getColor(R.color.color_main),
                                getColor(R.color.color_main), BubbleGradient.VERTICAL
                            )
                        else->
                            BubbleGradient(
                                getColor(R.color.color_main),
                                getColor(R.color.color_main), BubbleGradient.VERTICAL
                            )
                    }
                }
            }
        }
        viewBinding.picker.bubbleSize = 8

        viewBinding.textView.setOnClickListener {

            viewBinding.picker.adapter = object : BubblePickerAdapter {
                override val totalCount = 15
                override fun getItem(position: Int): PickerItem {
                    return PickerItem().apply {
                        gradient = when(position%2){
                            0->
                                BubbleGradient(
                                    getColor(R.color.color_main),
                                    getColor(R.color.color_main), BubbleGradient.VERTICAL
                                )
                            1->
                                BubbleGradient(
                                    getColor(R.color.color_main),
                                    getColor(R.color.color_main), BubbleGradient.VERTICAL
                                )
                            2->
                                BubbleGradient(
                                    getColor(R.color.color_main),
                                    getColor(R.color.color_main), BubbleGradient.VERTICAL
                                )
                            else->
                                BubbleGradient(
                                    getColor(R.color.color_main),
                                    getColor(R.color.color_main), BubbleGradient.VERTICAL
                                )
                        }
                    }
                }
            }
            viewBinding.picker.clear()
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