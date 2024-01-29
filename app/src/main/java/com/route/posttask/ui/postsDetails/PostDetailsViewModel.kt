package com.route.posttask.ui.postsDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.entity.Post
import com.route.domain.usecase.PostDetailsUseCase
import com.route.posttask.util.ControlState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val postDetailsUseCase: PostDetailsUseCase):
ViewModel(){

    val postLiveData = MutableLiveData<Post?>()
    val state = MutableLiveData<ControlState>()

    fun fetchPostDetails(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state.postValue(ControlState.LOADING)
                val res = postDetailsUseCase.invoke(id)
                postLiveData.postValue(res)
                state.postValue(ControlState.SUCCESS)
                Log.d("post details test" , res?.body.toString())
            }catch (e: Exception){
                state.postValue(ControlState.ERROR)
            }
        }
    }
}