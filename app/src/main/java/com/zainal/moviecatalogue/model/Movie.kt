package com.zainal.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var imageSrc: Int,
    var name: String,
    var description: String,
    var release: String) : Parcelable