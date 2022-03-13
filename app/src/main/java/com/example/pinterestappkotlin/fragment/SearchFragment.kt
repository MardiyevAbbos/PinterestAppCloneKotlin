package com.example.pinterestappkotlin.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.adapter.HomeAdapter
import com.example.pinterestappkotlin.adapter.PopularPinterestAdapter
import com.example.pinterestappkotlin.model.*
import com.example.pinterestappkotlin.network.RetrofitHttp
import com.example.pinterestappkotlin.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(){

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: SearchFragment? = null
        fun newInstance(): SearchFragment {
            if (instance == null) {
                instance = SearchFragment()
            }
            return instance!!
        }
    }

    private lateinit var ll_back: LinearLayout
    private lateinit var ll_cancel: LinearLayout
    private lateinit var edt_search: EditText
    private lateinit var ll_popular_pinterest: LinearLayout
    private lateinit var adapterSearch: HomeAdapter
    private lateinit var recyclerView_search : RecyclerView
    private lateinit var adapterCollection: PopularPinterestAdapter
    private lateinit var recyclerView_collection : RecyclerView
    private var page: Int = 1
    private var pageCollections: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireContext() as MainActivity).setLightStatusBar()
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val iv_back : ImageView = view.findViewById(R.id.iv_back)
        ll_back = view.findViewById(R.id.ll_back)
        val tv_cancel: TextView = view.findViewById(R.id.tv_cancel)
        ll_cancel = view.findViewById(R.id.ll_cancel)
        edt_search = view.findViewById(R.id.edt_search)
        ll_popular_pinterest = view.findViewById(R.id.ll_popular_pinterest)

        recyclerView_collection = view.findViewById(R.id.recyclerView_popular)
        recyclerView_collection.layoutManager = GridLayoutManager(requireContext(), 2)
        adapterCollection.items = getMyCollects(++pageCollections)
        recyclerView_collection.adapter = adapterCollection

        recyclerView_search = view.findViewById(R.id.recyclerView_search)
        recyclerView_search.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_search.adapter = adapterSearch
        recyclerView_search.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    apiSearchPhotos(edt_search.text.toString(), ++page)
                }
            }
        })

        allFunctionEdtSearch()

        checkIsEmptySearchList()

        iv_back.setOnClickListener{
            changeIconAndLL()
            edt_search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_search_black, 0, R.drawable.icon_camera_black, 0)
            adapterSearch.photoList.clear()
            (requireActivity() as MainActivity).closeKeyBoard()
        }  // clicked back icon

        tv_cancel.setOnClickListener{
            changeIconAndLL()
            adapterSearch.photoList.clear()
            (requireActivity() as MainActivity).closeKeyBoard()
        }  // clicked cancel icon

    }

    private fun clickItemCollection(search: String){
        adapterSearch.photoList.clear()
        apiSearchPhotos(search, 1)

        ll_popular_pinterest.visibility = View.GONE
        recyclerView_search.visibility = View.VISIBLE
        ll_cancel.visibility = View.GONE
        ll_back.visibility = View.VISIBLE

        edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        edt_search.text.insert(0, search)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun allFunctionEdtSearch(){
        edt_search.setOnTouchListener { _, event ->
            if (MotionEvent.ACTION_UP == event?.action) {
                edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_camera_black, 0)
                ll_cancel.visibility = View.VISIBLE
                ll_back.visibility = View.GONE

                if(event.rawX >= (edt_search.right - edt_search.compoundDrawables[2].bounds.width())) {
                    edt_search.text.clear()
                    return@setOnTouchListener true;
                }

            }
            false
        }  // clicked editText for writing

        edt_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            //actionId == EditorInfo.IME_ACTION_SEARCH &&
            if (edt_search.text.isNotEmpty()) {
                adapterSearch.photoList.clear()
                apiSearchPhotos(edt_search.text.toString(), 1)

                ll_popular_pinterest.visibility = View.GONE
                recyclerView_search.visibility = View.VISIBLE
                ll_cancel.visibility = View.GONE
                ll_back.visibility = View.VISIBLE


                edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                (requireActivity() as MainActivity).closeKeyBoard()
            }else{
                changeIconAndLL()
            }
            false
        })  // this work when click search icon in Keyboard

        edt_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_search.text.isNotEmpty()){
                    edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_clear_black, 0)
                }else{
                    edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_camera_black, 0)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun checkIsEmptySearchList(){
        if (adapterSearch.photoList.isNotEmpty()){
            ll_popular_pinterest.visibility = View.GONE
            recyclerView_search.visibility = View.VISIBLE
        }else{
            changeIconAndLL()
        }
    }

    private fun apiSearchPhotos(search: String, pageState: Int){
        RetrofitHttp.pinterestService.getSearchPhotoList(search, pageState).enqueue(object : Callback<SearchPhotoList>{
            override fun onResponse(
                call: Call<SearchPhotoList>,
                response: Response<SearchPhotoList>
            ) {
                Logger.d("@@@SSearch", response.body().toString())
                val results: ArrayList<PhotoItem> = response.body()!!.results as ArrayList<PhotoItem>
                adapterSearch.addPhotos(results)
            }

            override fun onFailure(call: Call<SearchPhotoList>, t: Throwable) {
                Logger.e("@@@ESearch", t.message.toString())
            }

        })
    }

    fun onBackPressed(): Boolean{
        if (ll_popular_pinterest.visibility == View.GONE){
            changeIconAndLL()
            adapterSearch.photoList.clear()
            return false
        }
        return true
    }

    private fun changeIconAndLL(){
        ll_popular_pinterest.visibility = View.VISIBLE
        recyclerView_search.visibility = View.GONE
        ll_cancel.visibility = View.GONE
        ll_back.visibility = View.GONE
        edt_search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_search_black, 0, R.drawable.icon_camera_black, 0)
        edt_search.text.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterSearch = HomeAdapter(requireActivity() as MainActivity)
        adapterCollection = PopularPinterestAdapter(requireContext()){
            clickItemCollection(it)
        }
        adapterCollection.items = getMyCollects(1)

        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    private fun getMyCollects(pageC: Int): ArrayList<MyCollection> {
        val collects = ArrayList<MyCollection>()

//        collects.add(MyCollection("PIZZA",
//            "https://images.unsplash.com/photo-1601924582970-9238bcb495d9?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
//        collects.add(MyCollection("Ice cream",
//            "https://images.unsplash.com/photo-1560008581-09826d1de69e?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
//        collects.add(MyCollection("Coffee Culture ☕ ",
//            "https://images.unsplash.com/photo-1567016530961-54fd42f2d51f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
//        collects.add(MyCollection("health",
//            "https://images.unsplash.com/photo-1553531889-65d9c41c2609?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
//        collects.add(MyCollection("New Year",
//            "https://images.unsplash.com/photo-1514446945-952d86c3449b?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
//        collects.add(MyCollection("Work from Anywhere",
//            "https://images.unsplash.com/photo-1584677626646-7c8f83690304?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))

        RetrofitHttp.pinterestService.listCollections(pageC).enqueue(object : Callback<ArrayList<WelcomeElement>>{
            override fun onResponse(call: Call<ArrayList<WelcomeElement>>, response: Response<ArrayList<WelcomeElement>>) {
                Logger.d("@@@CollectionsD", response.body().toString())
                for (collection in response.body()!!){
                    collects.add(MyCollection(collection.title!!, collection.coverPhoto?.urls?.thumb!!))
                }
            }

            override fun onFailure(call: Call<ArrayList<WelcomeElement>>, t: Throwable) {
                Logger.e("E@@@CollectionsE", t.message.toString())
            }

        })

        return collects
    }

}