package com.example.kotlin.training.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.training.data.Filter
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.data.MediaProviderImpl
import com.example.kotlin.training.ui.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val mediaProvider: MediaProvider = MediaProviderImpl,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible:LiveData<Boolean> get() = _progressVisible

    private val _items = MutableLiveData<List<MediaItem>>()
    val items:LiveData<List<MediaItem>> get() = _items

    private val _navigateToDetail = MutableLiveData<Event<Int>>()
    val navigateToDetail:LiveData<Event<Int>> get() = _navigateToDetail

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