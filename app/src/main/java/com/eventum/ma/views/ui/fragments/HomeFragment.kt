package com.eventum.ma.views.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.HomePresenter
import com.eventum.ma.views.adapters.EventsHorizontalItemsAdapter
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.ui.activities.EventDetails
import com.eventum.ma.views.ui.activities.SignUpActivity
import com.eventum.ma.views.views.HomeViewInt
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),EventListener, HomeViewInt {

    private var homePresenter: HomePresenter? = null
    private lateinit var todayEventsAdapter: EventsHorizontalItemsAdapter
    private lateinit var officialEventsAdapter: EventsHorizontalItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = activity?.getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
        val s = preferences!!.getString("session","")
        todayEventsAdapter = EventsHorizontalItemsAdapter(this)
        officialEventsAdapter = EventsHorizontalItemsAdapter(this)
        homePresenter = HomePresenter(this)

        rvTodayEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvOfficialEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }

        getTodayEvents()
        getOfficialEvents()
    }

    override fun onEventClicked(event: EventModel, position: Int) {
        val intent = Intent(view!!.context  , EventDetails::class.java)
        event.id_event="1"
        intent.putExtra("EVENT", event.id_event);
        startActivity(intent)
    }

    override fun showTodayEvents(events: ArrayList<EventModel>?) {
        todayEventsAdapter.updateEvents(events)
        try {
            rvTodayEvents!!.adapter = todayEventsAdapter
            dataLoaded()
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showOfficialEvents(events: ArrayList<EventModel>?) {
        officialEventsAdapter.updateEvents(events)
        try {
            rvOfficialEvents!!.adapter = officialEventsAdapter
            dataLoaded()
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getTodayEvents() {
        homePresenter?.getTodayEvents()

    }

    override fun getOfficialEvents() {
        homePresenter?.getOfficialEvents()
    }

    private fun dataLoaded() {
//        rlBaseEv.visibility = View.INVISIBLE
    }


}
