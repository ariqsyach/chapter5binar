package com.example.chap5p2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class AdapterViewPager(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val dataFragment = mutableListOf(FirstFragment(), SecondFragment(), ThirdFragment())

    override fun getItem(position: Int): Fragment {
        return dataFragment[position]
    }

    override fun getCount(): Int {
        return dataFragment.size
    }
}
