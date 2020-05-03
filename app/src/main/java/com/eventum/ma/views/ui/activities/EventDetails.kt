package com.eventum.ma.views.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.EventProfilePresenter
import com.eventum.ma.views.adapters.EventsHorizontalItemsAdapter
import com.eventum.ma.views.listeners.EventListener
import kotlinx.android.synthetic.main.activity_event_details.event_profile_description
import kotlinx.android.synthetic.main.activity_event_details.event_profile_name
import kotlinx.android.synthetic.main.activity_event_details.event_profile_type
import kotlinx.android.synthetic.main.activity_event_details.rlBaseEventProfile
import kotlinx.android.synthetic.main.activity_group_details.*

class EventDetails : AppCompatActivity(), EventListener {

    private lateinit var eventProfilePresenter: EventProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
    }

    override fun onStart() {
        super.onStart()
        val id = "1"
        val eventId = intent.getStringExtra("EVENT")
        if (eventId != null) {
            eventProfilePresenter = ViewModelProviders.of(this).get(EventProfilePresenter::class.java)
            eventProfilePresenter.refresh(eventId, id)
            observeViewModel()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun observeViewModel() {
        eventProfilePresenter.eventDetails.observe(this, Observer { event ->
            event.let {
                event_profile_name.text = event.name
                event_profile_description.text = event.description
                event_profile_type.text = event.event_type

                val eventImage: ImageView = findViewById(R.id.event_image)

                event.image = "https://source.unsplash.com/random"

                Glide.with(this)
                    .load(event.image)
                    .into(eventImage)
                dataLoaded()
            }
            rvGroupEvents.apply {
                layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            }
        })

        eventProfilePresenter.isLoading.observe(this, Observer<Boolean> {
            if (it != null) {
                val a = true
            }
//                rlBase.visibility = View.INVISIBLE
        })
    }

    private fun dataLoaded() {
        rlBaseEventProfile.visibility = View.INVISIBLE
    }

    override fun onEventClicked(event: EventModel, position: Int) {

    }

}
