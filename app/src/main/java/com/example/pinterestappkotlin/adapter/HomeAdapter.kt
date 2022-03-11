package com.example.pinterestappkotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.fragment.DetailsFragment
import com.example.pinterestappkotlin.model.PhotoItem
import com.example.pinterestappkotlin.model.PhotoList
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class HomeAdapter(var context: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photoList: ArrayList<PhotoItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addPhotos(items: ArrayList<PhotoItem>) {
        photoList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_home_photo_view, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    inner class HomeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val iv_photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        private val tv_description: TextView = view.findViewById(R.id.tv_description)
        private val ll_item_click: LinearLayout = view.findViewById(R.id.ll_item_click)
        fun bind(position: Int) {
//            Glide.with(context).load(item.urls.thumb).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_photo)
            val item = photoList[position]
            Picasso.get().load(item.urls.thumb).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_photo)
            tv_description.text = item.description

            ll_item_click.setOnClickListener {
                val items: ArrayList<PhotoItem> = ArrayList()
                var idPos = position
                when {
                    photoList.size > 10 && position < 6 -> {
                        for (i in 0..10) items.add(photoList[i])
                    }
                    photoList.size > 10 && position > 5 && position < photoList.size - 6 -> {
                        for(i in position-5.. position+4) items.add(photoList[i])
                        idPos = 5
                    }
                    photoList.size > 10 && position > photoList.size - 6 -> {
                        for (i in photoList.size-12 until photoList.size) items.add(photoList[i])
                        idPos = 12 + position - photoList.size
                    }
                    else -> { items.addAll(photoList) }
                }

                context.replaceFragment(DetailsFragment(items, idPos))
            }

        }
    }

}