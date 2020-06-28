package com.example.kotlin.training.model

data class MediaItem(val title: String, val url: String, val type: Type) {
    enum class Type { PHOTO, VIDEO }
}