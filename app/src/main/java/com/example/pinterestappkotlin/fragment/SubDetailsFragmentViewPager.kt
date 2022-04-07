package com.example.pinterestappkotlin.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.adapter.HomeAdapter
import com.example.pinterestappkotlin.database.entity.PhotoRoom
import com.example.pinterestappkotlin.database.repository.PhotoRepository
import com.example.pinterestappkotlin.model.PhotoItem
import com.example.pinterestappkotlin.model.RelatedPhotos
import com.example.pinterestappkotlin.network.RetrofitHttp
import com.example.pinterestappkotlin.utils.Dialogs
import com.example.pinterestappkotlin.utils.Logger
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubDetailsFragmentViewPager(var item: PhotoItem): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sub_details_view_pager, container, false)
        initViews(view)
        return view
    }

    private lateinit var adapter: HomeAdapter

    @SuppressLint("SetTextI18n")
    private fun initViews(view: View) {
        val iv_photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        val iv_sponsor: ShapeableImageView = view.findViewById(R.id.iv_sponsor)
        val iv_more: ImageView = view.findViewById(R.id.iv_more)
        val sponsor_fullName: TextView = view.findViewById(R.id.tv_sponsor_fullName)
        val sponsor_followers: TextView = view.findViewById(R.id.tv_sponsor_followers)
        val tv_description: TextView = view.findViewById(R.id.tv_description)
        val tv_save_room: TextView = view.findViewById(R.id.tv_save_room)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        Picasso.get().load(item.urls?.small).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_photo)
        Picasso.get().load(item.user?.profile_image?.large).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_sponsor)

        sponsor_fullName.text = item.user?.name
        sponsor_followers.text = "${item.likes!!} Followers"
        tv_description.text = "${item.description}"

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = HomeAdapter(requireActivity() as MainActivity)
        recyclerView.adapter = adapter
        apiRelatedPhotos(item.id!!)

        tv_save_room.setOnClickListener{
            PhotoRepository(requireActivity().application).savePhoto(PhotoRoom(photoItem = item))
            Toast.makeText(context, "\tImage Saved to Profile\t", Toast.LENGTH_SHORT).show()
        }

        iv_more.setOnClickListener{
            Dialogs.showBottomSheetDialog(requireContext(), item)
        }

    }

    private fun apiRelatedPhotos(id: String) {
        RetrofitHttp.pinterestService.getRelatedPhotos(id)
            .enqueue(object : Callback<RelatedPhotos> {
                override fun onResponse(
                    call: Call<RelatedPhotos>,
                    response: Response<RelatedPhotos>
                ) {
                    Logger.d("@@@", response.message().toString())
                    val relatedPhotos: RelatedPhotos = response.body() as RelatedPhotos
                    adapter.addPhotos(relatedPhotos.results!!)
                }

                override fun onFailure(call: Call<RelatedPhotos>, t: Throwable) {
                    Logger.e("@@@" ,t.message.toString())
                }

            })
    }

}