package com.route.posttask.ui.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.route.domain.entity.Post
import com.route.e_commerce.databinding.ActivityMainBinding
import com.route.posttask.ui.postsDetails.PostDetailsActivity
import com.route.posttask.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    private lateinit var viewbinding: ActivityMainBinding
    private var postAdapter = PostsAdapter()

    private val viewModel: PostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        initViews()



        lifecycleScope.launch {
            viewModel.invokeAction(PostsManager.Action.LoadPosts)
        }

        viewModel.state.observe(this , ::hanldeState)
        viewModel.event.observe(this , ::handleEvents)
    }

    private fun handleEvents(event: PostsManager.Event?) {
        when(event){
            is PostsManager.Event.NavigateToPostDetails -> handlePostClicked(event.id)
            null -> {}
        }
    }

    private fun handlePostClicked(id: String) {
        val intent = Intent(this , PostDetailsActivity::class.java)
        intent.putExtra(Constants.POST_ID , id)
        startActivity(intent)
    }

    private fun hanldeState(state: PostsManager.State?) {

        when(state){
            is PostsManager.State.Error -> handleError()
            is PostsManager.State.Loading -> handleLoading()
            is PostsManager.State.Success -> handleSuccess(state.data)
            null -> {}
        }
    }

    private fun handleSuccess(data: List<Post?>?) {
        viewbinding.successView.isVisible = true
        viewbinding.errorView.isVisible = false
        viewbinding.loadingView.isVisible = false
        postAdapter.submitList(data)
    }

    private fun handleLoading() {
        viewbinding.successView.isVisible = false
        viewbinding.errorView.isVisible = false
        viewbinding.loadingView.isVisible = true
    }

    private fun handleError() {
        viewbinding.successView.isVisible = false
        viewbinding.errorView.isVisible = true
        viewbinding.loadingView.isVisible = false
    }

    private fun initViews() {
        viewbinding.postsRecycler.adapter = postAdapter

        postAdapter.onPostClickListener = PostsAdapter.OnPostClickListener { id->

            viewModel.invokeAction(PostsManager.Action.PostClicked(id.toString()))


        }

        viewbinding.tryAgainBtn.setOnClickListener {
            viewModel.invokeAction(PostsManager.Action.LoadPosts)
        }
    }


}