package com.route.data.model

import com.google.gson.annotations.SerializedName
import com.route.domain.entity.Post

data class PostDto(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
){
	fun mapToPosts(): Post{
		return Post(
			id = id,
			title = title,
			body = body,
			userId = userId
		)
	}
}