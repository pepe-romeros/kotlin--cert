package com.example.kotlin.training.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin.training.util.Logger

abstract class BaseActivity : AppCompatActivity(), Logger {

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}