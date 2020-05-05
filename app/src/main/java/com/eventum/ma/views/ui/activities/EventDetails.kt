package com.eventum.ma.views.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.eventum.ma.R
import com.eventum.ma.presenters.EventProfilePresenter
import com.eventum.ma.views.adapters.AssistantItemAdapter
import com.eventum.ma.views.adapters.CommentItemAdapter
import com.eventum.ma.views.views.EventProfileInt
import kotlinx.android.synthetic.main.activity_event_details.*


class EventDetails() : AppCompatActivity(),EventProfileInt{

    private lateinit var eventProfilePresenter: EventProfilePresenter
    private lateinit var commentAdapter: CommentItemAdapter
    private lateinit var assistantAdapter: AssistantItemAdapter

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
        val preferences = this.getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
        val id = preferences!!.getString("userID", "")
        val eventId = intent.getStringExtra("EVENT")
        if (eventId != null && id !=null) {
            commentAdapter = CommentItemAdapter()
            assistantAdapter = AssistantItemAdapter()
            eventProfilePresenter = ViewModelProviders.of(this).get(EventProfilePresenter::class.java)
            eventProfilePresenter.refresh(eventId, id)
            observeViewModel()
            rvComments.apply {
                layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            }
            rvAssistants.apply {
                layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
             }
            val followButton = findViewById<Button>(R.id.seguirEventButton)
            followButton.setOnClickListener {
                followButton.text = "Asistiendo"
                followEvent(id,eventId)
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun observeViewModel() {
//        eventProfilePresenter.isFollowing.observe(this, Observer { following ->
//            following.let {
//                println("FOLLOWING")
//                println(it)
//            }
//        })
        eventProfilePresenter.eventDetails.observe(this, Observer { event ->
            event.let {

                event_profile_name.text = event.name
                event_profile_description.text = event.description
                event_profile_type.text = event.event_type
                event_start_date.text = event.eventStartDate
                event_end_date.text = event.eventFinishDate
                event_url.text = event.url
                event_status.text= event.status
//                event_interested.text = event.interested.size.toString()
                event_assistant.text = event.assistant.size.toString()

                commentAdapter.updateData(event.comments)
                assistantAdapter.updateData(event.assistant)

                rvComments!!.adapter = commentAdapter
                rvAssistants!!.adapter = assistantAdapter

                val eventImage: ImageView = findViewById(R.id.event_image)
                event.image = "https://source.unsplash.com/random"

                Glide.with(this)
                    .load(event.image)
                    .into(eventImage)
                dataLoaded()
            }

        })
    }

    private fun dataLoaded() {
        loadingEventDetails.visibility = View.VISIBLE
        loadingEventDetails2.visibility = View.INVISIBLE
    }

    override fun followEvent(id: String, idEvent: String) {
        eventProfilePresenter.refreshFollow(id,idEvent)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.navbar_details -> {
//                finish()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


}
