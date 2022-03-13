package com.example.pinterestappkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.fragment.SearchFragment
import com.example.pinterestappkotlin.model.MyCollection
import com.google.android.material.imageview.ShapeableImageView

class PopularPinterestAdapter(private val context: Context, val search: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<MyCollection> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_pinterest_view, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PopularViewHolder){
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PopularViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(item: MyCollection){
            val image: ShapeableImageView = view.findViewById(R.id.iv_photo)
            val title: TextView = view.findViewById(R.id.tv_title)
            val fl_item_collection: FrameLayout = view.findViewById(R.id.fl_item_collection)

            Glide.with(context).load(item.image).into(image)
            title.text = item.title
            fl_item_collection.setOnClickListener{
                search.invoke(item.title)
            }
        }
    }

}