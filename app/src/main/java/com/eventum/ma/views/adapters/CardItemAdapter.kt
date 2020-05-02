package com.eventum.ma.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eventum.ma.R
import kotlin.collections.ArrayList
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.views.listeners.GroupListener

class CardItemAdapter(private val groupListener: GroupListener) : RecyclerView.Adapter<CardItemAdapter.ViewHolder>() {

    private var groupList = ArrayList<GroupModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_group, parent, false))

    override fun getItemCount() = groupList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = groupList[position]

        holder.groupItemName.text = group.name
        holder.groupItemOwner.text = group.type
        holder.groupItemDescription.text = group.description

        Glide.with(holder.itemView.context)
            .load(group.image)
            .into(holder.groupItemImage)

        holder.itemView.setOnClickListener {
            groupListener.onGroupClicked(group, position)
        }

    }

    //el adaptador recibe los datos
    fun updateData(data: ArrayList<GroupModel>?) {
        groupList.clear()
        if (data != null) {
            groupList.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupItemName: TextView = itemView.findViewById(R.id.group_item_name)
        val groupItemOwner: TextView = itemView.findViewById(R.id.group_item_type)
        val groupItemDescription: TextView = itemView.findViewById(R.id.group_item_description)
        val groupItemImage: ImageView = itemView.findViewById(R.id.group_item_image)
    }

}