package com.example.kotlin.training.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.lifecycle.*
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
fun ImageView.loadUrl(imageUrl: String) { Glide.with(this).load(imageUrl).into(this) }
fun View.setVisible(visible: Boolean) { visibility = if (visible) View.VISIBLE else View.GONE }
// endregion extensions to add functionality to Views and ViewGroups

// region extensions to handle user navigation
inline fun <reified T: Activity> Context.startActivity(vararg pairs: Pair<String, Any?>) {
    Intent(this, T::class.java).apply { putExtras(bundleOf(*pairs)) }.also(::startActivity)
}
// endregion extensions to handle user navigation

// region extend LifeCycle Owner
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer(observer))
}
inline fun <reified T: ViewModel> ViewModelStoreOwner.getViewModel(body: T.() -> Unit): T {
    return ViewModelProvider(this)[T::class.java].apply(body)
}
fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, observer: (T) -> Unit) {
    observe(liveData){ it.getContentIfNotHandled()?.let(observer) }
}
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>
// endregion extend LifeCycle Owner