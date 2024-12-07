package ru.zatsoft.animation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(val name: String, var image: Int, val price: Double):Parcelable
