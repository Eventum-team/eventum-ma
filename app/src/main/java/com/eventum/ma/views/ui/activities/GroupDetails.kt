package com.eventum.ma.views.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupProfilePresenter
import com.eventum.ma.views.adapters.EventItemAdapter
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.views.GroupProfileViewInt
import kotlinx.android.synthetic.main.fragment_group_profile.*

class GroupDetails : AppCompatActivity(), EventListener, GroupProfileViewInt {

    private var groupProfilePresenter: GroupProfilePresenter? = null
    private lateinit var eventAdapter: EventItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)
        val id = intent.getStringExtra("GROUP")
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val id = "1"
        eventAdapter = EventItemAdapter(this)
        groupProfilePresenter = GroupProfilePresenter(this)

        rvGroupProfileEvents.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }

        getGroupProfile(id)
        //llamar datos, no se si toca otro adapter
        getEventsByGroup(id)
//        showGroups(groups)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
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
