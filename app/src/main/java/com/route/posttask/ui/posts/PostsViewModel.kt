package com.route.posttask.ui.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.entity.Post
import com.route.domain.usecase.GetAllPostsUseCase
import com.route.posttask.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val getAllPostsUseCase: GetAllPostsUseCase):
ViewModel() , PostsManager.ViewModel{

    private val _event = SingleLiveEvent<PostsManager.Event>()
    override val event = _event

    private val _states = SingleLiveEvent<PostsManager.State>()
    override val state = _states

    override fun invokeAction(action: PostsManager.Action) {
        when(action){
            is PostsManager.Action.LoadPosts -> getPostsData()
            is PostsManager.Action.PostClicked -> postClicked(action.id.toString())
        }
    }


    private fun getPostsData(){
        viewModelScope.launch {
            try {
                _states.postValue(PostsManager.State.Loading())
                val res =  getAllPostsUseCase.invoke()
                _states.postValue(PostsManager.State.Success(res))
                Log.d("test posts" , "lenght is  ${ res?.size?:5874 }" )
            }catch (e: Exception){
                Log.d("test posts" , e.message.toString())
                _states.postValue(PostsManager.State.Error(e.localizedMessage?:"something went wrong"))
            }
        }
    }

    private fun postClicked(id: String){
        _event.postValue(PostsManager.Event.NavigateToPostDetails(id))
    }
}