package com.eventum.ma.views.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.presenters.HomePresenter
import com.eventum.ma.presenters.UserProfilePresenter
import com.eventum.ma.presenters.presenters.UserProfilePresenterInt
import com.eventum.ma.views.adapters.EventsHorizontalItemsAdapter
import com.eventum.ma.views.adapters.GroupsHorizontalItemsAdapter
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.listeners.GroupListener
import com.eventum.ma.views.ui.activities.EventDetails
import com.eventum.ma.views.views.UserProfileEventInt
import kotlinx.android.synthetic.main.fragment_user_profile.*


class UserProfileFragment : Fragment(), EventListener,GroupListener, UserProfileEventInt {

    private lateinit var userProfilePresenter: UserProfilePresenter
    private lateinit var myGroupsAdapter: GroupsHorizontalItemsAdapter
    private lateinit var myEventsAdapter: EventsHorizontalItemsAdapter
    private lateinit var attendanceEventsAdapter: EventsHorizontalItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myGroupsAdapter = GroupsHorizontalItemsAdapter(this)
        myEventsAdapter = EventsHorizontalItemsAdapter(this)
        attendanceEventsAdapter = EventsHorizontalItemsAdapter(this)
        val id = "4"

        userProfilePresenter = ViewModelProviders.of(this).get(UserProfilePresenter::class.java)
        userProfilePresenter.refresh(id)
        observeViewModel()
        rvAttendanceEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvMyEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvMyGroups.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }

        getUserInfo(id)

    }

    fun observeViewModel() {

        userProfilePresenter.userModel.observe(this, Observer<UserModel> { usermMod ->
            usermMod.let {
                myEventsAdapter.updateEvents(usermMod.eventsCreated)
                myGroupsAdapter.updateEvents(usermMod.groupsFollowing)
            }

        })

//        homePresenter.isLoading.observe(this, Observer<Boolean> {
//            if(it != null)
//                rlBase.visibility = View.INVISIBLE
//        })
    }



    override fun getUserInfo(id: String) {
       userProfilePresenter?.getUserInfo(id)
    }

    override fun showUserInfo(user: UserModel) {
        //Ids in fragment XML
        profile_name.text = user.name
        profile_career.text = user.career
        profile_event_count.text = "5"
        profile_group_count.text = "4"
        Glide.with(view!!.context)
            .load(user.image)
            .into(profile_image)

    }

    override fun showEventsCreatedByUser(events: ArrayList<EventModel>) {
//        Toast.makeText(view!!.context, "user created", Toast.LENGTH_SHORT).show()
        myEventsAdapter.updateEvents(events)
        try {
            rvMyEvents!!.adapter = myEventsAdapter
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showGroupsFollowedByUser(groups: ArrayList<GroupModel>){
        myGroupsAdapter.updateEvents(groups)
        try {
            rvMyGroups!!.adapter = myGroupsAdapter
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showEventsAttendedBuUser(events: ArrayList<EventModel>) {
        attendanceEventsAdapter.updateEvents(events)
        try {
            rvAttendanceEvents!!.adapter = attendanceEventsAdapter
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onEventClicked(event: EventModel, position: Int) {
        val intent = Intent(view!!.context  , EventDetails::class.java)
        event.id_event="1"
        intent.putExtra("EVENT", event.id_event);
        startActivity(intent)
    }

    override fun onGroupClicked(group: GroupModel, position: Int) {

    }

}
