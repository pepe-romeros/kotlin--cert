package com.example.kotlin.training.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.data.MediaProviderImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val mediaProvider: MediaProvider = MediaProviderImpl,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _item = MutableLiveData<MediaItem>()
    val item:LiveData<MediaItem> get() = _item

    fun onCreate(itemId: Int) {
        viewModelScope.launch {
            val items = withContext(ioDispatcher) { mediaProvider.getItems() }
            val mediaItem = items.firstOrNull { it.id == itemId }

            mediaItem?.let {
                _item.value = mediaItem
            }

        }
    }

}