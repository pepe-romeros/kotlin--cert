package com.example.kotlin.training.ui.home

import com.example.kotlin.training.data.Filter
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(private val view:View, private val scope: CoroutineScope) {

    interface View {
        fun setProgressVisibility(visible: Boolean)
        fun updateItems(items: List<MediaItem>)
        fun navigateToDetail(itemId: Int)
    }

    fun onFilteredClicked(filter: Filter) {
        scope.launch {
            view.setProgressVisibility(true)
            val items = withContext(Dispatchers.IO) { getFilteredItems(filter)}
            view.updateItems(items)
            view.setProgressVisibility(false)
        }
    }

    private fun getFilteredItems(filter: Filter): List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when (filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }
    }

    fun onItemClicked(item: MediaItem) {
        view.navigateToDetail(item.id)
    }

}