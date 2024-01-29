package com.route.data.remoteDateSourceImpl

import android.util.Log
import com.route.data.api.WebServices
import com.route.data.model.PostDto
import com.route.data.remoteDataSourceContract.PostsDataSource
import com.route.domain.entity.Post
import javax.inject.Inject

class PostsDataSourceImpl @Inject constructor(private val webServices: WebServices): PostsDataSource {
    override suspend fun getAllPosts(): List<Post?>? {
        return webServices.getPosts().map {
            it.mapToPosts()
        }
    }
}