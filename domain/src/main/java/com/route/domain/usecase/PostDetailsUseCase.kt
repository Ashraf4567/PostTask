package com.route.domain.usecase

import com.route.domain.entity.Post
import com.route.domain.repository.PostDetailsRepository
import javax.inject.Inject

class PostDetailsUseCase @Inject constructor(private val postDetailsRepository: PostDetailsRepository) {

    suspend fun invoke(id: String): Post?{
        return postDetailsRepository.getPostDetails(id)
    }
}