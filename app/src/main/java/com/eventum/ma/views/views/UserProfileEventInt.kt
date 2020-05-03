package com.eventum.ma.views.views

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel

interface UserProfileEventInt {
    fun getUserInfo(id: String)

    fun showUserInfo(user: UserModel)

    fun showEventsCreatedByUser(events: ArrayList<EventModel>)

    fun showGroupsFollowedByUser(groups: ArrayList<GroupModel>)

    fun showEventsAttendedBuUser(events: ArrayList<EventModel>)


}