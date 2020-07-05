package com.example.kotlin.training.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.ui.asLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val mediaProvider: MediaProvider,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _item = MutableLiveData<MediaItem>()
    val item = _item.asLiveData()

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