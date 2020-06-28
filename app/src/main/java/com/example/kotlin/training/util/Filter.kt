package com.example.kotlin.training.util

import com.example.kotlin.training.model.MediaItem

sealed class Filter {
    object None: Filter()
    class ByType(val type:MediaItem.Type): Filter()
}