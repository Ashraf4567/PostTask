package com.route.data.remoteDateSourceImpl

import com.route.data.remoteDataSourceContract.PostsDataSource
import com.route.data.remoteDataSourceContract.PostsDetailsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)

class Di {

    @Provides
    fun providePostsDataSource(postsDataSourceImpl: PostsDataSourceImpl): PostsDataSource{
        return postsDataSourceImpl
    }

    @Provides
    fun providePostDetailsDataSource(postDetailsDataSourceImpl: PostDetailsDataSourceImpl)
    : PostsDetailsDataSource{
        return postDetailsDataSourceImpl
    }

}