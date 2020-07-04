package com.example.kotlin.training.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.training.R
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.databinding.ActivityMainBinding
import com.example.kotlin.training.ui.detail.DetailActivity
import com.example.kotlin.training.data.MediaItem.Type
import com.example.kotlin.training.data.Filter
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.ui.startActivity

class MainActivity : BaseActivity(), MainPresenter.View {

    private val presenter = MainPresenter(this, lifecycleScope)
    private val adapter = MediaAdapter(presenter::onItemClicked)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter
        presenter.onFilteredClicked(Filter.None)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when(item.itemId) {
            R.id.filter_photos -> Filter.ByType(Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(Type.VIDEO)
            else -> Filter.None
        }

        presenter.onFilteredClicked(filter)
        return super.onOptionsItemSelected(item)
    }

    override fun setProgressVisibility(visible: Boolean) {
        binding.progress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun updateItems(items: List<MediaItem>) {
        adapter.items = items
    }

    override fun navigateToDetail(itemId: Int) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to itemId)
    }

}
