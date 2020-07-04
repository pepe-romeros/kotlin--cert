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
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.ui.startActivity

import kotlinx.coroutines.*

class MainActivity : BaseActivity() {

    private val adapter = MediaAdapter {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it.id)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
        updateItems()
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
        updateItems(filter)
        return super.onOptionsItemSelected(item)
    }

    private fun updateItems(filter: Filter = Filter.None) {
        lifecycleScope.launch {
            binding.progress.visibility = View.VISIBLE

            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }

            adapter.items = when (filter) {
                Filter.None -> items
                is Filter.ByType -> items.filter { it.type == filter.type }
            }

            binding.progress.visibility = View.GONE
        }
    }

}
