package com.route.domain.usecase

import com.route.domain.entity.Post
import com.route.domain.repository.PostRepository
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend fun invoke(): List<Post?>?{
        return postRepository.getPosts()
    }
}