package com.route.domain.repository

import com.route.domain.entity.Post

interface PostDetailsRepository {

    suspend fun getPostDetails(id: String): Post?
}