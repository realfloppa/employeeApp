package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.github.lastachkin.employeeapp.databinding.ActivityMainBinding
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                val layoutParams = binding.searchView.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(0, 0, 200, 0)
                binding.searchView.layoutParams = layoutParams
            }
        }

        binding.viewPager.adapter = EmployeeViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position){
                0 -> "All"
                1 -> "Designers"
                2 -> "Managers"
                3 -> "Developers"
                4 -> "Analysts"
                else -> "TODO"
            }
        }.attach()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}