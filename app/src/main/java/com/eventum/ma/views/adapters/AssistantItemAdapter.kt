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
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.views.listeners.GroupListener

class AssistantItemAdapter() : RecyclerView.Adapter<AssistantItemAdapter.ViewHolder>() {

    private var assistantList = ArrayList<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_assistant, parent, false))

    override fun getItemCount() = assistantList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = assistantList[position]

        holder.assistantItemName.text = user.name
        holder.assistantItemCareer.text = user.career




    }

    //el adaptador recibe los datos
    fun updateData(data: List<UserModel>?) {
        assistantList.clear()
        if (data != null) {
            assistantList.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assistantItemName: TextView = itemView.findViewById(R.id.assistant_name)
        val assistantItemCareer: TextView = itemView.findViewById(R.id.assistant_career)

    }

}