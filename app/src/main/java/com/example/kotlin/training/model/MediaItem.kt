package com.example.kotlin.training.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaItem(val title: String, val url: String, val type: Type): Parcelable {
    enum class Type { PHOTO, VIDEO }
}