package com.eventum.ma.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupProfilePresenter
import com.eventum.ma.views.adapters.EventItemAdapter
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.views.GroupProfileViewInt
import kotlinx.android.synthetic.main.fragment_group_profile.*

class GroupProfileFragment : Fragment(),
        EventListener, GroupProfileViewInt {

    private var groupProfilePresenter: GroupProfilePresenter? = null
    private lateinit var eventAdapter: EventItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = "1"
        eventAdapter = EventItemAdapter(this)
        groupProfilePresenter = GroupProfilePresenter(this)

        rvGroupProfileEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }

        getGroupProfile(id)
        //llamar datos, no se si toca otro adapter
        getEventsByGroup(id)
//        showGroups(groups)
    }

    override fun showEventsByGroup(events: ArrayList<EventModel>?) {
        if (events != null) {
            eventAdapter.updateData(events)
        }
        try {
            rvGroupProfileEvents!!.adapter = eventAdapter
            dataLoaded()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showGroupProfile(group: GroupModel?) {
        if (group != null) {
            group_profile_name.text = group.name
            group_profile_type.text = group.type
            group_profile_description.text = group.description
        }
    }

    override fun getEventsByGroup(id : String) {
        groupProfilePresenter?.getEventsByGroup(id)
    }

    override fun getGroupProfile(id: String) {
        groupProfilePresenter?.getGroupProfile(id)
    }

    override fun onEventClicked(event: EventModel, position: Int) {

    }
    private fun dataLoaded() {
        rlBaseGroupProfile.visibility = View.INVISIBLE
    }




}