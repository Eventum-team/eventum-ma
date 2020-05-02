package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel

interface UserProfilePresenterInt {
    fun getUserInfo(id: String)

    fun getEventsCreatedByUser(id: String)

    fun getGroupsFollowedByUser(id: String)

    fun getEventsAttendedBuUser(id: String)

    fun showUserInfo(user: UserModel)

    fun showEventsCreatedByUser(events: ArrayList<EventModel>)

    fun showGroupsFollowedByUser(groups: ArrayList<GroupModel>)

    fun showEventsAttendedBuUser(events: ArrayList<EventModel>)
}