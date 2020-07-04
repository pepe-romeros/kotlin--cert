package com.example.kotlin.training.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel() {

    private val _item = MutableLiveData<MediaItem>()
    val item:LiveData<MediaItem> get() = _item

    fun onCreate(itemId: Int) {
        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }
            val mediaItem = items.firstOrNull { it.id == itemId }

            mediaItem?.let {
                _item.value = mediaItem
            }

        }
    }

}