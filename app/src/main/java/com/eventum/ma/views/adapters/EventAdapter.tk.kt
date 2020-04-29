package com.eventum.ma.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eventum.ma.R
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.views.listeners.GroupListener

class EventAdapter(private val eventListener: GroupListener) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var groupList = ArrayList<GroupModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.group_item, parent, false))

    override fun getItemCount() = groupList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = groupList[position]

        holder.groupItemName.text = event.name
        holder.groupItemOwner.text = event.type
        holder.groupItemDescription.text = event.description

        Glide.with(holder.itemView.context)
            .load(event.image)
            .into(holder.groupItemImage)

        holder.itemView.setOnClickListener {
            eventListener.onConferenceClicked(event, position)
        }

    }

    //el adaptador recibe los datos
    fun updateData(data: List<GroupModel>) {
        groupList.clear()
        groupList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupItemName: TextView = itemView.findViewById(R.id.group_item_name)
        val groupItemOwner: TextView = itemView.findViewById(R.id.group_item_type)
        val groupItemDescription: TextView = itemView.findViewById(R.id.group_item_description)
        val groupItemImage: ImageView = itemView.findViewById(R.id.group_item_image)
    }

}