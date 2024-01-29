package com.route.data.remoteDataSourceContract

import com.route.data.model.PostDto
import com.route.domain.entity.Post

interface PostsDataSource {
    suspend fun getAllPosts(): List<Post?>?
}