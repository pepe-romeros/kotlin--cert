package com.example.kotlin.training.data

sealed class Filter {
    object None: Filter()
    class ByType(val type:MediaItem.Type): Filter()
}