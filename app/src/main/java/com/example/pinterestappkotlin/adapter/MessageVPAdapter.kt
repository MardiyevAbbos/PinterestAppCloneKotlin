package com.example.pinterestappkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

class MessageVPAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var fragments: ArrayList<Fragment> = ArrayList()
    private var titles: ArrayList<String> = ArrayList()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    fun addTitle(title: String) {
        titles.add(title)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}