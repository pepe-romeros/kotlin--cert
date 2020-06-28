package com.example.kotlin.training

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.databinding.ActivityMainBinding
import com.example.kotlin.training.model.MediaItem.Type
import com.example.kotlin.training.util.MediaProvider
import com.example.kotlin.training.util.toast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : BaseActivity() {

    private val adapter = MediaAdapter { toast(it.title) }
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
        updateItems(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun updateItems(@IdRes filterId: Int = R.id.filter_all) {
        lifecycleScope.launch {
            binding.progress.visibility = View.VISIBLE

            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }

            adapter.items = when (filterId) {
                R.id.filter_photos -> items.filter { it.type == Type.PHOTO }
                R.id.filter_videos -> items.filter { it.type == Type.VIDEO }
                else -> items
            }

            binding.progress.visibility = View.GONE
        }
    }

}
