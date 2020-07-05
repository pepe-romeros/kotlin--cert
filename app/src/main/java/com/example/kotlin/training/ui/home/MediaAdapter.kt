package com.example.kotlin.training.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.training.R
import com.example.kotlin.training.databinding.ViewMediaItemBinding

import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaItem.Type
import com.example.kotlin.training.ui.inflate
import com.example.kotlin.training.ui.loadUrl
import kotlin.properties.Delegates

class MediaAdapter(private val listener: (MediaItem) -> Unit): RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items: List<MediaItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_media_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaItem = items[position]
        holder.bind(mediaItem)
        holder.itemView.setOnClickListener { listener(mediaItem) }
    }

    override fun getItemCount(): Int = items.size

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