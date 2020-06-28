package com.example.kotlin.training.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// region extensions to show messages to the user

fun Context.toast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

fun RecyclerView.ViewHolder.toast(message: String, length: Int = Toast.LENGTH_LONG) {
    itemView.context.toast(message, length)
}

// endregion extensions to show messages to the user

// region extensions to add functionality to Views and ViewGroups

fun ViewGroup.inflate(@LayoutRes layout: Int, attached: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attached)
}

fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

// endregion extensions to add functionality to Views and ViewGroups

// region extensions to handle user navigation
inline fun <reified T: Activity> Context.startActivity(destinationActivity: Class<T>, intent: Intent = Intent(this, T::class.java)) {
    startActivity(intent)
}
// endregion extensions to handle user navigation

