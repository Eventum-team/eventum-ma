package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel

interface GroupProfilePresenterInt {
    fun showGroupProfile(group: GroupModel?)
    fun showEventsByGroup(groups: ArrayList<EventModel>?)
    //Presenter
    fun getGroupProfile(id: String)
    fun getEventsByGroup(id: String)
}