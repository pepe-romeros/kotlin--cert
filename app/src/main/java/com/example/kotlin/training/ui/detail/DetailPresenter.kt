package com.example.kotlin.training.ui.detail

import android.view.View
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaItem.Type
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.ui.loadUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPresenter(private val view: View, private val scope: CoroutineScope) {

    interface View {
        fun setTitleBar(title: String)
        fun setImage(url: String)
        fun setVideoIndicatorVisible(visible: Boolean)
    }

    fun onCreate(itemId: Int) {
        scope.launch {
            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }
            val mediaItem = items.firstOrNull { it.id == itemId }

            mediaItem?.let {
                view.setTitleBar(mediaItem.title)
                view.setImage(mediaItem.url)
                view.setVideoIndicatorVisible(mediaItem.type == Type.VIDEO)
            }

        }
    }

}