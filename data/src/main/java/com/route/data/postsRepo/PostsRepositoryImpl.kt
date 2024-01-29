package com.route.data.postsRepo

import com.route.data.remoteDataSourceContract.PostsDataSource
import com.route.domain.entity.Post
import com.route.domain.repository.PostRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val dataSource: PostsDataSource): PostRepository {

    override suspend fun getPosts(): List<Post?>? {
        return dataSource.getAllPosts()
    }
}