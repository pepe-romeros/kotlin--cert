package com.example.kotlin.training.ui.detail

import android.os.Bundle
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.data.MediaItem.Type
import com.example.kotlin.training.databinding.ActivityDetailBinding
import com.example.kotlin.training.ui.loadUrl
import com.example.kotlin.training.ui.observe
import com.example.kotlin.training.ui.setVisible
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class DetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_ID = "DetailActivity:id"
    }

    private val viewModel: DetailViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.apply {
            observe(item) {
                supportActionBar?.title = it.title
                binding.detailThumb.loadUrl(it.url)
                binding.detailVideoIndicator.setVisible(it.type == Type.VIDEO)
            }
        }
        viewModel.onCreate(intent.getIntExtra(EXTRA_ID, -1))
    }

}