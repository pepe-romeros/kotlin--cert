package com.example.kotlin.training

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.training.base.BaseActivity
import com.example.kotlin.training.util.getItems
import com.example.kotlin.training.util.toast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
    }

    private fun initRecycler() {
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = MediaAdapter(getItems())
        toast("Recycler initialized")
    }

}
