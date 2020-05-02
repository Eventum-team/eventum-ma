package com.eventum.ma.views.views

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel

interface GroupProfileViewInt {
    fun showGroupProfile(group: GroupModel?)
    fun showEventsByGroup(groups: ArrayList<EventModel>?)
    //Presenter
    fun getGroupProfile(id: String)
    fun getEventsByGroup(id: String)
}