package com.eventum.ma.views.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.EventsPresenter
import com.eventum.ma.views.adapters.EventItemAdapter
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.ui.activities.EventDetails
import com.eventum.ma.views.views.EventsViewInt
import kotlinx.android.synthetic.main.fragment_event_list.*
import java.util.*
import kotlin.collections.ArrayList


class EventListFragment : Fragment(),EventListener,EventsViewInt {

    private  var eventsPresenter: EventsPresenter? = null
    private lateinit var eventAdapter: EventItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventAdapter = EventItemAdapter(this)
        eventsPresenter = EventsPresenter(this)
        rvEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }
        getEvents()
//        showGroups(groups)
    }
    override fun showEvents(events: ArrayList<EventModel>?) {
        if (events != null) {
            eventAdapter.updateData(events)
        }
        try {
            rvEvents!!.adapter = eventAdapter
            dataLoaded()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showEventsByName(groups: ArrayList<EventModel>?) {

    }

    override fun getEvents() {
        eventsPresenter?.getEvents()
    }

    override fun getEventsByName() {
        eventsPresenter?.getEventsByName()
    }

    override fun onEventClicked(event: EventModel, position: Int) {
        val intent = Intent(view!!.context  , EventDetails::class.java)
        event.id_event="1"
        intent.putExtra("EVENT", event.id_event);
        startActivity(intent)
    }
    private fun dataLoaded() {
        rlBaseEvents.visibility = View.INVISIBLE
    }
}
