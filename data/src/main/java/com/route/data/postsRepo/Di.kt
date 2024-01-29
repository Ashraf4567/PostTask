package com.route.data.postsRepo

import com.route.domain.repository.PostDetailsRepository
import com.route.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
    abstract fun bindPostsRepo(repo: PostsRepositoryImpl): PostRepository
    @Binds
    abstract fun bindPostDetailsRepo(repo: PostDetailsRepositoryImpl): PostDetailsRepository
}