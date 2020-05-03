package com.eventum.ma.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.listeners.GroupListener
import kotlin.collections.ArrayList

class GroupsHorizontalItemsAdapter(private val groupsListener: GroupListener) :
    RecyclerView.Adapter<GroupsHorizontalItemsAdapter.ViewHolder>() {

    private var groupList = ArrayList<GroupModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_event, parent, false
        )
    )

    override fun getItemCount() = groupList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = groupList[position]

        holder.groupItemName.text = group.name
        holder.groupItemOwner.text = group.type

        group.image = "https://source.unsplash.com/random"
        Glide.with(holder.itemView.context)
            .load(group.image)
            .into(holder.groupItemImage)

        holder.itemView.setOnClickListener {
            groupsListener.onGroupClicked(group, position)
        }
    }

    //el adaptador recibe los datos
    fun updateEvents(data: ArrayList<GroupModel>?) {
        groupList.clear()
        if (data != null) {
            groupList.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupItemName: TextView = itemView.findViewById(R.id.home_event_title)
        val groupItemOwner: TextView = itemView.findViewById(R.id.home_event_owner)
        val groupItemImage: ImageView = itemView.findViewById(R.id.home_event_image)
    }

}