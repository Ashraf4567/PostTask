package com.route.posttask.ui.posts

import androidx.lifecycle.LiveData
import com.route.domain.entity.Post

class PostsManager {

    interface ViewModel{
        val event: LiveData<Event>
        val state: LiveData<State>
        fun invokeAction(action: Action)
    }

    sealed class State{
        class Loading(): State()
        class Success(val data: List<Post?>?): State()
        class Error(val message: String): State()
    }

    sealed class Action{
        object LoadPosts : Action()
        class PostClicked(val id: String) : Action()
    }

    sealed class Event{
        class NavigateToPostDetails(val id: String): Event()
    }
}