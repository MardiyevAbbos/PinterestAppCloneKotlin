package com.example.pinterestappkotlin.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.adapter.RoomPhotosAdapter
import com.example.pinterestappkotlin.database.entity.PhotoRoom
import com.example.pinterestappkotlin.database.repository.PhotoRepository
import com.example.pinterestappkotlin.model.MyProfile
import com.example.pinterestappkotlin.network.RetrofitHttp
import com.example.pinterestappkotlin.utils.Logger
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.imageview.ShapeableImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var fragment: ProfileFragment? = null

        fun newInstance(): ProfileFragment {
            if (fragment == null) {
                fragment = ProfileFragment()
            }
            return fragment!!
        }
    }

    private lateinit var collapsingTBR: CollapsingToolbarLayout
    private lateinit var fm_inToolbar: FrameLayout
    private lateinit var ll_inToolbar: LinearLayout
    private lateinit var appBarLayout: AppBarLayout

    private lateinit var iv_profile: ShapeableImageView
    private lateinit var iv_profile_inToolbar: ShapeableImageView
    private lateinit var tv_fullname: TextView
    private lateinit var tv_fullname_inToolbar: TextView
    private lateinit var tv_username: TextView
    private lateinit var tv_followers_count: TextView
    private lateinit var tv_followings_count: TextView
    var profileImage: String = ""

    private lateinit var adapterRoom: RoomPhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiMyProfile()

        adapterRoom = RoomPhotosAdapter(requireActivity() as MainActivity)
        adapterRoom.photoList = getPhotosRoom()

        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).bnvVisibility()
        (requireContext() as MainActivity).setLightStatusBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        collapsingTBR = view.findViewById(R.id.collapsingTBR)
        fm_inToolbar = view.findViewById(R.id.fm_inToolbar)
        ll_inToolbar = view.findViewById(R.id.ll_inToolbar)
        appBarLayout = view.findViewById(R.id.appBar)

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                kotlin.math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0 -> {
                    // Collapsed
                    fm_inToolbar.visibility = View.VISIBLE
                    ll_inToolbar.visibility = View.INVISIBLE
                }
                verticalOffset == 0 -> {
                    // Expanded
                    fm_inToolbar.visibility = View.INVISIBLE
                    ll_inToolbar.visibility = View.VISIBLE
                    Glide.with(requireContext()).load(profileImage).into(iv_profile_inToolbar)
                }
                else -> {
                    // Idle
                    fm_inToolbar.visibility = View.INVISIBLE
                    ll_inToolbar.visibility = View.VISIBLE
                    Glide.with(requireContext()).load(profileImage).into(iv_profile_inToolbar)
                }
            }
        })

        iv_profile = view.findViewById(R.id.iv_profile)
        iv_profile_inToolbar = view.findViewById(R.id.iv_profile_inToolbar)
        tv_fullname = view.findViewById(R.id.tv_profile_name)
        tv_fullname_inToolbar = view.findViewById(R.id.tv_fullName_inToolbar)
        tv_username = view.findViewById(R.id.tv_profile_username)
        tv_followers_count = view.findViewById(R.id.tv_profile_followers)
        tv_followings_count = view.findViewById(R.id.tv_profile_followings)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_savedPhotos)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapterRoom

        if (adapterRoom.photoList.size != getPhotosRoom().size){
            adapterRoom.updateItems(getPhotosRoom())
        }

    }

    private fun apiMyProfile() {
        RetrofitHttp.pinterestService.getMyProfile().enqueue(object : Callback<MyProfile> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<MyProfile>, response: Response<MyProfile>) {
                Logger.d("@@@ProfileD", response.body().toString())
                val profile: MyProfile = response.body()!!
                Glide.with(requireContext()).load(profile.profileImage?.large!!).into(iv_profile)
                profileImage = profile.profileImage.large
                Glide.with(requireContext()).load(profileImage).into(iv_profile_inToolbar)

                tv_fullname.text = profile.name!!
                tv_fullname_inToolbar.text = profile.name
                tv_username.text = profile.username!!
                tv_followers_count.text = "${profile.followersCount!!} followers"
                tv_followings_count.text = "${profile.followingCount!!} following"

            }

            override fun onFailure(call: Call<MyProfile>, t: Throwable) {
                Logger.e("@@@ProfileE", t.message.toString())
            }

        })
    }

    private fun getPhotosRoom() = PhotoRepository(application = requireActivity().application).getAllPhotos() as ArrayList<PhotoRoom>


}