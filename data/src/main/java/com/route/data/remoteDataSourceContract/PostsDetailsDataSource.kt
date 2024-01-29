package com.route.data.remoteDataSourceContract

import com.route.data.model.PostDto
import com.route.domain.entity.Post
import java.net.IDN

interface PostsDetailsDataSource {
    suspend fun getAllPosts(id: String): Post?
}