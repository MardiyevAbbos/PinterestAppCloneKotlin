package com.example.pinterestappkotlin.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.database.entity.PhotoRoom
import com.example.pinterestappkotlin.database.repository.PhotoRepository
import com.example.pinterestappkotlin.model.PhotoItem
import com.example.pinterestappkotlin.model.RelatedPhotos
import com.example.pinterestappkotlin.network.RetrofitHttp
import com.example.pinterestappkotlin.utils.Logger
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsAdapter(var context: MainActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<PhotoItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_details_photo_view, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DetailsViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class DetailsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val iv_photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        private val iv_sponsor: ShapeableImageView = view.findViewById(R.id.iv_sponsor)
        private val sponsor_fullName: TextView = view.findViewById(R.id.tv_sponsor_fullName)
        private val sponsor_followers: TextView = view.findViewById(R.id.tv_sponsor_followers)
        private val tv_description: TextView = view.findViewById(R.id.tv_description)
        private val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        private val tv_save_room: TextView = view.findViewById(R.id.tv_save_room)
        private lateinit var adapter: HomeAdapter

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val item = items[position]
            Picasso.get().load(item.urls?.small).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_photo)
            Picasso.get().load(item.user?.profileImage?.large).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_sponsor)

            sponsor_fullName.text = item.user?.name
            sponsor_followers.text = "${item.likes!!} Followers"
            tv_description.text = "${item.description}"

            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = HomeAdapter(context)
            recyclerView.adapter = adapter
            apiRelatedPhotos(item.id!!)

            tv_save_room.setOnClickListener{
                val description = item.description ?: ""
                PhotoRepository(application = context.application)
                    .savePhoto(PhotoRoom(
                        item.id,
                        photo = item.urls?.regular!!,
                        description = description,
                        color = item.color!!
                    ))
                Toast.makeText(context, "This photo is saved in RoomDatabase", Toast.LENGTH_SHORT).show()
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


}