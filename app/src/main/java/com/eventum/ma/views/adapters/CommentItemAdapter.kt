package com.eventum.ma.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eventum.ma.R
import com.eventum.ma.models.models.CommentModel
import kotlin.collections.ArrayList
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.views.listeners.GroupListener

class CommentItemAdapter() : RecyclerView.Adapter<CommentItemAdapter.ViewHolder>() {

    private var commentList = ArrayList<CommentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_comment, parent, false))

    override fun getItemCount() = commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentList[position]

        holder.commentItemName.text = comment.name
        holder.commentItemtext.text = comment.text
        holder.commentItemlike.text = comment.likes
        holder.commentItemdislike.text = comment.dislikes


    }

    //el adaptador recibe los datos
    fun updateData(data: List<CommentModel>?) {
        commentList.clear()
        if (data != null) {
            commentList.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentItemName: TextView = itemView.findViewById(R.id.comment_name)
        val commentItemtext: TextView = itemView.findViewById(R.id.comment_text)
        val commentItemlike: TextView = itemView.findViewById(R.id.comment_like_count)
        val commentItemdislike: TextView = itemView.findViewById(R.id.comment_dislike_count)
    }

}