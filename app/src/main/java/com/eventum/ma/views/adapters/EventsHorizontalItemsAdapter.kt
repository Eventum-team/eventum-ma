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
import kotlin.collections.ArrayList

class EventsHorizontalItemsAdapter(private val eventListener: EventListener) :
    RecyclerView.Adapter<EventsHorizontalItemsAdapter.ViewHolder>() {

    private var todayEventList = ArrayList<EventModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_event, parent, false
        )
    )

    override fun getItemCount() = todayEventList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = todayEventList[position]

        holder.groupItemName.text = event.name
        holder.groupItemOwner.text = event.id_owner

        Glide.with(holder.itemView.context)
            .load(event.image)
            .into(holder.groupItemImage)

        holder.itemView.setOnClickListener {
            eventListener.onEventClicked(event, position)
        }
    }

    //el adaptador recibe los datos
    fun updateEvents(data: List<EventModel>?) {
        todayEventList.clear()
        if (data != null) {
            todayEventList.addAll(data)

        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupItemName: TextView = itemView.findViewById(R.id.home_event_title)
        val groupItemOwner: TextView = itemView.findViewById(R.id.home_event_owner)
        val groupItemImage: ImageView = itemView.findViewById(R.id.home_event_image)
    }

}