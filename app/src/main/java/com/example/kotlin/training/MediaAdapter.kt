package com.example.kotlin.training

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.kotlin.training.model.MediaItem
import com.example.kotlin.training.util.inflate
import com.example.kotlin.training.util.loadUrl

class MediaAdapter(private val mediaList:List<MediaItem>): RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_media_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mediaList[position])
    }

    override fun getItemCount(): Int = mediaList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title:TextView = itemView.findViewById(R.id.mediaTitle)
        private val image:ImageView = itemView.findViewById(R.id.mediaThumb)

        fun bind(mediaItem: MediaItem) {
            title.text = mediaItem.title
            image.loadUrl(mediaItem.url)
        }
    }

}