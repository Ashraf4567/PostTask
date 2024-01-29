package com.route.data.postsRepo

import com.route.data.remoteDateSourceImpl.PostDetailsDataSourceImpl
import com.route.domain.entity.Post
import com.route.domain.repository.PostDetailsRepository
import javax.inject.Inject

class PostDetailsRepositoryImpl @Inject constructor(
    private val postDetailsDataSourceImpl: PostDetailsDataSourceImpl): PostDetailsRepository{

    override suspend fun getPostDetails(id: String): Post? {
        return postDetailsDataSourceImpl.getAllPosts(id)
    }
}