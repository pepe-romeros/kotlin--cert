package com.example.kotlin.training

import android.os.Bundle
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.databinding.ActivityMainBinding
import com.example.kotlin.training.util.getItems
import com.example.kotlin.training.util.toast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = MediaAdapter(getItems()) { toast(it.title) }
        toast("MainActivity created")

    }

}
