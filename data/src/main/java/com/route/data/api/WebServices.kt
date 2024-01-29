package com.route.data.api


import com.route.data.model.PostDto
import retrofit2.http.GET
import retrofit2.http.Path


interface WebServices {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{id}")
    suspend fun getPostsDetails(
        @Path ("id") id: String?
    ): PostDto



}