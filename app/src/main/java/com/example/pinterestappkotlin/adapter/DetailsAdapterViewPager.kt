package com.example.pinterestappkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

class DetailsAdapterViewPager(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var fragments: ArrayList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}