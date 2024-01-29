package com.route.posttask.ui.postsDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.route.e_commerce.databinding.ActivityPostDetailsBinding
import com.route.posttask.util.Constants
import com.route.posttask.util.ControlState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityPostDetailsBinding
    private val viewModel: PostDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val id = intent.getStringExtra(Constants.POST_ID)


        lifecycleScope.launch {
            viewModel.fetchPostDetails(id.toString())
        }

        viewModel.postLiveData.observe(this){
            viewBinding.post = it
        }

        viewModel.state.observe(this , ::handleState)
        }

    private fun handleState(controlState: ControlState?) {
        when(controlState){
            ControlState.LOADING -> handleLoading()
            ControlState.ERROR -> handleError()
            ControlState.SUCCESS -> handleSuccess()
            else -> handleError()
        }


}



    private fun handleError() {
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = false
        Toast.makeText(this , "something went wrong" , Toast.LENGTH_LONG).show()
    }

    private fun handleSuccess() {
        viewBinding.successView.isVisible = true
        viewBinding.loadingView.isVisible = false


    }

    private fun handleLoading() {
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = true
    }
}