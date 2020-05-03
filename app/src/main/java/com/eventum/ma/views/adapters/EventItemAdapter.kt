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
import com.eventum.ma.views.listeners.EventListener

class EventItemAdapter(private val eventListener: EventListener) : RecyclerView.Adapter<EventItemAdapter.ViewHolder>() {

    private var eventList = ArrayList<EventModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_group, parent, false))

    override fun getItemCount() = eventList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]

        holder.groupItemName.text = event.name
        holder.groupItemOwner.text = event.id_owner
        holder.groupItemDescription.text = event.description
//        if(event.image == ""){
            event.image = "https://source.unsplash.com/random"
//        }

        Glide.with(holder.itemView.context)
            .load(event.image)
            .into(holder.groupItemImage)

        holder.itemView.setOnClickListener {
            eventListener.onEventClicked(event, position)
        }

    }

    //el adaptador recibe los datos
    fun updateData(data: List<EventModel>) {
        eventList.clear()
        if (data != null) {
            eventList.addAll(data)
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