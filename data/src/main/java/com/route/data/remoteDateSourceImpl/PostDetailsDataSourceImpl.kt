package com.route.data.remoteDateSourceImpl

import com.route.data.api.WebServices
import com.route.data.remoteDataSourceContract.PostsDetailsDataSource
import com.route.domain.entity.Post
import java.net.IDN
import javax.inject.Inject

class PostDetailsDataSourceImpl @Inject constructor(private val webServices: WebServices): PostsDetailsDataSource{
    override suspend fun getAllPosts(id: String): Post? {
        return webServices.getPostsDetails(id).mapToPosts()
    }
}