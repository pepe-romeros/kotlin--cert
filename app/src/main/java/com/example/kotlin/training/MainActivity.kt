package com.example.kotlin.training

import android.os.Bundle
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.databinding.ActivityMainBinding
import com.example.kotlin.training.util.getItems
import com.example.kotlin.training.util.toast

class MainActivity : BaseActivity() {

    private val adapter by lazy { MediaAdapter { toast(it.title) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.items = getItems()
        binding.recycler.adapter = adapter
    }

}
