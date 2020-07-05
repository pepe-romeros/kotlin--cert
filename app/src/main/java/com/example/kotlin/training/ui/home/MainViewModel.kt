package com.example.kotlin.training.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.training.data.Filter
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.ui.Event
import com.example.kotlin.training.ui.asLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val mediaProvider: MediaProvider,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible = _progressVisible.asLiveData()

    private val _items = MutableLiveData<List<MediaItem>>()
    val items = _items.asLiveData()

    private val _navigateToDetail = MutableLiveData<Event<Int>>()
    val navigateToDetail = _navigateToDetail.asLiveData()

    fun onFilteredClicked(filter: Filter) {
        viewModelScope.launch {
            _progressVisible.value = true
            _items.value = withContext(ioDispatcher) { getFilteredItems(filter)}
            _progressVisible.value = false
        }
    }

    private fun getFilteredItems(filter: Filter): List<MediaItem> {
        return mediaProvider.getItems().let { media ->
            when (filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }
    }

    fun onItemClicked(item: MediaItem) {
        _navigateToDetail.value = Event(item.id)
    }

}