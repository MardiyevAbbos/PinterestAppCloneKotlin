package com.example.pinterestappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.adapter.DetailsAdapterViewPager
import com.example.pinterestappkotlin.model.PhotoItem

class DetailsFragmentViewPager(var items: ArrayList<PhotoItem>, var position: Int) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAdapter()

        (requireContext() as MainActivity).bnvHide()
        (requireContext() as MainActivity).clearLightStatusBar()
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).bnvHide()
        setAdapter()
        refreshAdapter()
        viewPager.currentItem = position
    }

    private lateinit var viewpagerAdapter: DetailsAdapterViewPager
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_view_pager, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        viewPager = view.findViewById(R.id.view_pager_details)

        refreshAdapter()
    }

    private fun setAdapter() {
        viewpagerAdapter = DetailsAdapterViewPager(childFragmentManager)
        for (item in items){
            viewpagerAdapter.addFragment(SubDetailsFragmentViewPager(item))
        }
    }

    private fun refreshAdapter() {
        viewPager.adapter = viewpagerAdapter
    }

}