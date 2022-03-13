package com.example.pinterestappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.adapter.HomeAdapter
import com.example.pinterestappkotlin.model.PhotoItem
import com.example.pinterestappkotlin.network.RetrofitHttp
import com.example.pinterestappkotlin.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    companion object {
        private var instance: HomeFragment? = null

        fun newInstance(): HomeFragment {
            if (instance == null) {
                instance = HomeFragment()
            }

            return instance!!
        }
    }

    private lateinit var adapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireContext() as MainActivity).setLightStatusBar()
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(view)
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HomeAdapter(requireActivity() as MainActivity)
        apiPhotoList(1) // For get info first Page

        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    override fun onResume() {
        super.onResume()

        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }


    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    apiPhotoList(++page)
                }
            }
        })

    }


    private fun apiPhotoList(pageCount: Int) {
        RetrofitHttp.pinterestService.listPhotos(pageCount)
            .enqueue(object : Callback<ArrayList<PhotoItem>> {
                override fun onResponse(
                    call: Call<ArrayList<PhotoItem>>,
                    response: Response<ArrayList<PhotoItem>>
                ) {
                    if (pageCount == 1){
                        adapter.addPhotos(response.body()!!)
                    }else {
                        adapter.addPhotos(response.body()!!)
                    }

                    Logger.d("@@@TTT", response.body().toString())
                }

                override fun onFailure(call: Call<ArrayList<PhotoItem>>, t: Throwable) {
                    Logger.e("@@@", t.message.toString())
                }

            })
    }

}