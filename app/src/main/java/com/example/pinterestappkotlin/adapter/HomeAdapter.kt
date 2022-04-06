package com.example.pinterestappkotlin.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.fragment.DetailsFragmentViewPager
import com.example.pinterestappkotlin.model.PhotoItem
import com.example.pinterestappkotlin.utils.Dialogs
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class HomeAdapter(var context: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var photoList: ArrayList<PhotoItem> = ArrayList()

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
        private val iv_more: ImageView = view.findViewById(R.id.iv_more)

        fun bind(position: Int) {
            val item = photoList[position]
//            Glide.with(context).load(item.urls.thumb).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_photo)
            Picasso.get().load(item.urls?.thumb).placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_photo)
            tv_description.text = item.description

            iv_photo.setOnClickListener {
                context.replaceFragment(DetailsFragmentViewPager(photoList, position))
            }

            iv_more.setOnClickListener{
                Dialogs.showBottomSheetDialog(context, item)
            }

        }
    }

}