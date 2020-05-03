package com.eventum.ma.views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.eventum.ma.R
import com.eventum.ma.presenters.EventProfilePresenter
import com.eventum.ma.views.ui.fragments.MapFragment
import kotlinx.android.synthetic.main.activity_event_details.*


class EventDetails : AppCompatActivity(){

    private lateinit var eventProfilePresenter: EventProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        val mapButton = findViewById<Button>(R.id.event_map_button)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
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
//                event_interested.text = event.interested.size
//                event_assistant.text = event.assistant
                event_start_date.text = event.eventStartDate
                event_end_date.text = event.eventFinishDate
                event_url.text = event.url
                event_status.text= event.status

                val eventImage: ImageView = findViewById(R.id.event_image)

                event.image = "https://source.unsplash.com/random"

                Glide.with(this)
                    .load(event.image)
                    .into(eventImage)
                dataLoaded()
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


}
