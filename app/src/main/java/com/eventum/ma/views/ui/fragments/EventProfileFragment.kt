package com.eventum.ma.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.EventProfilePresenter
import com.eventum.ma.views.views.EventProfileViewInt
import kotlinx.android.synthetic.main.fragment_event_profile.*
import kotlinx.android.synthetic.main.fragment_event_profile.view.*
import kotlinx.android.synthetic.main.item_group.view.*

class EventProfileFragment : Fragment(), EventProfileViewInt {

    private var event = EventModel()
    private var eventProfilePresenter: EventProfilePresenter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = "1"

        eventProfilePresenter = EventProfilePresenter(this)

        getEventProfile(id)
//        showGroups(groups)
    }

    override fun showEventProfile(event: EventModel?) {
    }

    override fun getEventProfile(id: String) {
        eventProfilePresenter?.getEventProfile(id)
    }

    private fun dataLoaded() {
        rlBaseEventProfile.visibility = View.INVISIBLE
    }




}