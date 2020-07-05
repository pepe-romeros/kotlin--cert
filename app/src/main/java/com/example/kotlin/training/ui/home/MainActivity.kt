package com.example.kotlin.training.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kotlin.training.R
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.data.Filter
import com.example.kotlin.training.data.MediaItem.Type
import com.example.kotlin.training.databinding.ActivityMainBinding
import com.example.kotlin.training.ui.*
import com.example.kotlin.training.ui.detail.DetailActivity
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: ActivityMainBinding
    private val adapter = MediaAdapter { viewModel.onItemClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apply {
            observe(progressVisible) { binding.progress.setVisible(it) }
            observe(items) { adapter.items = it }
            observeEvent(navigateToDetail) { navigateToDetail(it) }
        }

        binding.recycler.adapter = adapter
        viewModel.onFilteredClicked(Filter.None)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByType(Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(Type.VIDEO)
            else -> Filter.None
        }

        viewModel.onFilteredClicked(filter)
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToDetail(itemId: Int) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to itemId)
    }

}
