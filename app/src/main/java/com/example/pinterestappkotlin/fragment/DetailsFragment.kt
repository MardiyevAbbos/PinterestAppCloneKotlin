package com.example.pinterestappkotlin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.adapter.DetailsAdapter
import com.example.pinterestappkotlin.model.PhotoItem

class DetailsFragment(var items: ArrayList<PhotoItem>, var position: Int) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireContext() as MainActivity).bnvHide()
        (requireContext() as MainActivity).clearLightStatusBar()
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).bnvHide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.scrollToPosition(position)

        recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = (recyclerView.layoutManager as LinearLayoutManager?)!!
                val activePosition = layoutManager.findFirstVisibleItemPosition()
                if (activePosition == RecyclerView.NO_POSITION) return
                Log.d("@@@@", "activePosition: $activePosition")
            }
        })

        refreshAdapter(recyclerView)

    }

    private fun refreshAdapter(rvDetails: RecyclerView) {
        val adapter = DetailsAdapter(requireActivity() as MainActivity, items)
        rvDetails.adapter = adapter
    }

}