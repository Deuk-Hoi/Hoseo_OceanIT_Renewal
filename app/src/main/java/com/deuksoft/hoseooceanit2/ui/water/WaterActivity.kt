package com.deuksoft.hoseooceanit2.ui.water

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.ActivityWaterBinding
import com.google.android.material.tabs.TabLayoutMediator

class WaterActivity : AppCompatActivity() {
    private lateinit var waterBinding: ActivityWaterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        waterBinding = ActivityWaterBinding.inflate(layoutInflater)
        setContentView(waterBinding.root)
        var tabLayoutTitle = arrayListOf(
            "DashBoard", "Network", "LOG"
        )

        waterBinding.waterPager.apply {
            adapter = ViewPagerAdapter(context as FragmentActivity)
        }

        TabLayoutMediator(waterBinding.waterTab, waterBinding.waterPager){ tab, position ->
            tab.text = tabLayoutTitle[position]
        }.attach()
    }
    private inner class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> DashBoardFragment()
                1 -> NetworkFragment()
                else -> LogFragment()
            }
        }

        override fun getItemCount(): Int = 3
    }
}