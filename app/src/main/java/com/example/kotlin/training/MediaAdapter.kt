package com.example.kotlin.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.training.model.MediaItem

class MediaAdapter(private val mediaList:List<MediaItem>): RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_media_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mediaList[position])
    }

    override fun getItemCount(): Int = mediaList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title:TextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val image:ImageView = itemView.findViewById<ImageView>(R.id.mediaThumb)

        fun bind(mediaItem: MediaItem) {
            title.text = mediaItem.title
            Glide.with(image).load(mediaItem.url).into(image)
        }
    }

}