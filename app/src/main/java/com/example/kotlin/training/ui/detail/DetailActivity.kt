package com.example.kotlin.training.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.databinding.ActivityDetailBinding
import com.example.kotlin.training.data.MediaItem.*
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.ui.loadUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : BaseActivity(), DetailPresenter.View {

    companion object {
        const val EXTRA_ID = "DetailActivity:id"
    }

    private val presenter = DetailPresenter(this, lifecycleScope)
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.onCreate(intent.getIntExtra(EXTRA_ID, -1))
    }

    override fun setTitleBar(title: String) {
        supportActionBar?.title = title
    }

    override fun setImage(url: String) {
        binding.detailThumb.loadUrl(url)
    }

    override fun setVideoIndicatorVisible(visible: Boolean) {
        binding.detailVideoIndicator.visibility = if (visible) View.VISIBLE else View.GONE
    }

}