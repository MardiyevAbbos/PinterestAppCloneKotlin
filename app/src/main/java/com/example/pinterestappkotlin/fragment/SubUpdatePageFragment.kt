package com.example.pinterestappkotlin.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity

class SubUpdatePageFragment() : Fragment() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var fragment: SubUpdatePageFragment? = null

        fun newInstance(): SubUpdatePageFragment {
            if (fragment == null) {
                fragment = SubUpdatePageFragment()
            }
            return fragment!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message_page_update, container, false)
    }

}