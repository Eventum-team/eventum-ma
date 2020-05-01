package com.eventum.ma.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.HomePresenter
import com.eventum.ma.views.adapters.HomeAdapter
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.views.HomeViewInt
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.android.synthetic.main.fragment_group_list.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),EventListener, HomeViewInt {

    private var homePresenter: HomePresenter? = null
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeOfficialAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       homeAdapter = HomeAdapter(this)
        homeOfficialAdapter = HomeAdapter(this)
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
        val bundle = bundleOf("event" to event)
        findNavController().navigate(R.id.eventDetailsDialogFragment, bundle)
    }

    override fun showTodayEvents(events: ArrayList<EventModel>?) {
        homeAdapter.updateEvents(events)
        try {
            rvTodayEvents!!.adapter = homeAdapter
            dataLoaded()
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showOfficialEvents(events: ArrayList<EventModel>?) {
        homeOfficialAdapter.updateEvents(events)
        try {
            rvOfficialEvents!!.adapter = homeOfficialAdapter
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
