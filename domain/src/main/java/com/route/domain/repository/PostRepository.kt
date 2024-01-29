package com.route.domain.repository

import com.route.domain.entity.Post

interface PostRepository {

    suspend fun getPosts(): List<Post?>?
}