package com.route.domain.entity

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Post(
	val id: Int? = null,
	val title: String? = null,
	val body: String? = null,
	val userId: Int? = null
) : Parcelable
