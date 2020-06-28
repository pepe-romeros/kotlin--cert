package com.example.kotlin.training

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.databinding.ActivityMainBinding
import com.example.kotlin.training.model.MediaItem.Type
import com.example.kotlin.training.util.getItems
import com.example.kotlin.training.util.toast

class MainActivity : BaseActivity() {

    private val adapter = MediaAdapter { toast(it.title) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.items = getItems()
        binding.recycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val items = getItems()

        adapter.items = when(item.itemId) {
            R.id.filter_photos -> items.filter { it.type == Type.PHOTO }
            R.id.filter_videos -> items.filter { it.type == Type.VIDEO }
            else -> items
        }

        return super.onOptionsItemSelected(item)
    }

}
