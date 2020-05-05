package com.eventum.ma.views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupProfilePresenter
import com.eventum.ma.views.adapters.EventItemAdapter
import com.eventum.ma.views.listeners.EventListener
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.activity_group_details.*

class GroupDetails : AppCompatActivity(), EventListener {

    private lateinit var groupProfilePresenter: GroupProfilePresenter
    private lateinit var eventAdapter: EventItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)
        val id = intent.getStringExtra("GROUP")
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val groupId = intent.getStringExtra("GROUP")
        eventAdapter = EventItemAdapter(this)
        if (groupId != null) {
            groupProfilePresenter =
                ViewModelProviders.of(this).get(GroupProfilePresenter::class.java)
            groupProfilePresenter.refresh(groupId)
            observeViewModel()
        }
        rvGroupEvents.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeViewModel() {
        groupProfilePresenter.eventDetails.observe(this, Observer { group ->
            group.let {
                eventAdapter.updateData(group.events)
                group_profile_name.text = group.name
                group_profile_description.text = group.description
                group_profile_type.text = group.type
                val eventImage: ImageView = findViewById(R.id.group_image)
                group.image = "https://source.unsplash.com/random"
                Glide.with(this)
                    .load(group.image)
                    .into(eventImage)
                dataLoaded()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onEventClicked(event: EventModel, position: Int) {
        val intent = Intent(this, EventDetails::class.java)
        intent.putExtra("EVENT", event.id_event);
        startActivity(intent)
    }


    private fun dataLoaded() {
        loadingGroupDetails.visibility = View.VISIBLE
        loadingGroupDetails2.visibility = View.INVISIBLE
        eventsByGroupLoading.visibility = View.INVISIBLE
    }

}
