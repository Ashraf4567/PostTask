package com.route.posttask.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.route.domain.entity.Post
import com.route.e_commerce.databinding.ItemPostBinding

class PostsAdapter : ListAdapter<Post, PostsAdapter.ViewHolder>(ProductDiffCallback()){

    class ViewHolder(private val itemBinding: ItemPostBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Post?) {
            itemBinding.post = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = ItemPostBinding
            .inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(item)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
        onPostClickListener.let {
            holder.itemView.setOnClickListener {
            onPostClickListener?.onPostClick(getItem(position).id!!)
            }
        }
    }


    var onPostClickListener: OnPostClickListener? = null
    fun interface OnPostClickListener{
        fun onPostClick(id: Int)
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}
