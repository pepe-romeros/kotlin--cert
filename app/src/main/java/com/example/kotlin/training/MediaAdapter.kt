package com.example.kotlin.training

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.training.databinding.ViewMediaItemBinding

import com.example.kotlin.training.model.MediaItem
import com.example.kotlin.training.model.MediaItem.Type
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

        private val binding = ViewMediaItemBinding.bind(itemView)

        fun bind(mediaItem: MediaItem) {
            with(binding) {
                mediaTitle.text = mediaItem.title
                mediaThumb.loadUrl(mediaItem.url)
                mediaVideoIndicator.visibility = when(mediaItem.type) {
                    Type.PHOTO -> View.GONE
                    Type.VIDEO -> View.VISIBLE
                }
            }
        }
    }

}