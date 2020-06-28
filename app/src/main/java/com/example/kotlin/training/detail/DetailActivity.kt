package com.example.kotlin.training.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.databinding.ActivityDetailBinding
import com.example.kotlin.training.model.MediaItem.*
import com.example.kotlin.training.util.MediaProvider
import com.example.kotlin.training.util.loadUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, -1)

        lifecycleScope.launch {
            val items =  withContext(Dispatchers.IO) { MediaProvider.getItems() }
            val mediaItem = items.firstOrNull { it.id == id }

            mediaItem?.let {
                supportActionBar?.title = mediaItem.title

                binding.detailThumb.loadUrl(mediaItem.url)
                binding.detailVideoIndicator.visibility = when(mediaItem.type) {
                    Type.PHOTO -> View.GONE
                    Type.VIDEO -> View.VISIBLE
                }
            }

        }
    }

}