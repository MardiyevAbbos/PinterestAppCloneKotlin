package com.example.pinterestappkotlin.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.adapter.MessageVPAdapter
import com.google.android.material.tabs.TabLayout

class MessageFragment() : Fragment() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var fragment: MessageFragment? = null

        fun newInstance(): MessageFragment {
            if (fragment == null) {
                fragment = MessageFragment()
            }
            return fragment!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAdapter()

        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()

        setAdapter()
        refreshAdapter()
        viewPager.currentItem = positionVP
    }

    private lateinit var viewpagerAdapter: MessageVPAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private var positionVP = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        viewPager = view.findViewById(R.id.viewPager_message)
        tabLayout = view.findViewById(R.id.tabLayout_pager)

        viewPager.isSaveEnabled = false // for ViewPager have not Exception when start OnCreateView of Fragment

        refreshAdapter()
        val iv_sort: ImageView = view.findViewById(R.id.iv_sort)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                when(position){
                    1 -> {
                        iv_sort.visibility = View.INVISIBLE
                        positionVP = 1
                    }
                    else -> {
                        iv_sort.visibility = View.VISIBLE
                        positionVP = 0
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })

    }

    private fun setAdapter() {
        viewpagerAdapter = MessageVPAdapter(childFragmentManager)
        viewpagerAdapter.addFragment(SubUpdatePageFragment())
        viewpagerAdapter.addFragment(SubMessagePageFragment())
        viewpagerAdapter.addTitle(getString(R.string.tab_updates))
        viewpagerAdapter.addTitle(getString(R.string.tab_messages))
    }

    private fun refreshAdapter() {
        viewPager.adapter = viewpagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}