package com.eventum.ma.views.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.EventProfilePresenter
import com.eventum.ma.views.views.EventProfileViewInt
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_event_profile.*

class EventDetails : AppCompatActivity(), EventProfileViewInt {

    private var event = EventModel()
    private var eventProfilePresenter: EventProfilePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        val event = intent.getStringExtra("EVENT")
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show()

    }

    override fun onStart() {
        super.onStart()
        val id = "1"
        eventProfilePresenter = EventProfilePresenter(this)
        getEventProfile(id)
    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun showEventProfile(event: EventModel?) {
        if (event != null) {
            event_profile_name.text = event.name
            event_profile_description.text = event.description
            event_profile_type.text = event.event_type
        }
        dataLoaded()
    }

    override fun getEventProfile(id: String) {
        eventProfilePresenter?.getEventProfile(id)
    }

    private fun dataLoaded() {
        rlBaseEventProfile.visibility = View.INVISIBLE
    }

}
