package com.example.kotlin.training.data

class FakeMediaProvider: MediaProvider {
    override fun getItems(): List<MediaItem> = emptyList()
}