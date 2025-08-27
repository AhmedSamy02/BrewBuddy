package com.example.brewbuddy.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeItem(
    val id: Int,
    val name: String,
    val description: String?,
    val ingredients: List<String>,
    val imageUrl: String?,
    val price: Double,
    val category: String
) : Parcelable

enum class Category { HOT, COLD }

