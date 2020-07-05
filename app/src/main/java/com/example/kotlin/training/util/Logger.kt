package com.example.kotlin.training.util

import android.util.Log

interface Logger {
    val tag: String
        get() = "${javaClass.name}_TAG"

    fun info(message: String) {
        Log.i(tag, message)
    }

    fun debug(message: String) {
        Log.d(tag, message)
    }

    fun error(message: String, error: Throwable) {
        Log.e(tag, message, error)
    }
}