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

        viewBinding.picker.adapter = object : BubblePickerAdapter {
            override val totalCount = 15

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
//                    title = titles[position]
                    gradient = when(position%3){
                        0->
                            BubbleGradient(
                                getColor(R.color.teal_200),
                                getColor(R.color.teal_200), BubbleGradient.VERTICAL
                            )
                        1->
                            BubbleGradient(
                                getColor(R.color.purple_200),
                                getColor(R.color.purple_500), BubbleGradient.VERTICAL
                            )
                        2->
                            BubbleGradient(
                                getColor(R.color.red_200),
                                getColor(R.color.red_500), BubbleGradient.VERTICAL
                            )
                        else->
                            BubbleGradient(
                                getColor(R.color.blue_200),
                                getColor(R.color.blue_500), BubbleGradient.VERTICAL
                            )
                    }
                }
            }
        }
        viewBinding.picker.bubbleSize = 8
        viewBinding.picker.centerImmediately = false

        viewBinding.picker.listener = null

        viewBinding.textView.setOnClickListener {
//            viewBinding.picker.adapter.totalCount = 8
            viewBinding.picker.bubbleSize = 8
            viewBinding.picker.centerImmediately = false

            viewBinding.picker.listener = null

            viewBinding.picker.adapter = object : BubblePickerAdapter {
                override val totalCount = 15

                override fun getItem(position: Int): PickerItem {
                    return PickerItem().apply {
//                    title = titles[position]
                        gradient = when(position%2){
                            0->
                                BubbleGradient(
                                    getColor(R.color.teal_200),
                                    getColor(R.color.teal_200), BubbleGradient.VERTICAL
                                )
                            1->
                                BubbleGradient(
                                    getColor(R.color.purple_200),
                                    getColor(R.color.purple_500), BubbleGradient.VERTICAL
                                )
                            2->
                                BubbleGradient(
                                    getColor(R.color.red_200),
                                    getColor(R.color.red_500), BubbleGradient.VERTICAL
                                )
                            else->
                                BubbleGradient(
                                    getColor(R.color.blue_200),
                                    getColor(R.color.blue_500), BubbleGradient.VERTICAL
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