package com.eventum.ma.presenters

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.models.repositories.UserRepository
import com.eventum.ma.presenters.presenters.UserProfilePresenterInt
import com.eventum.ma.views.views.EventsViewInt
import com.eventum.ma.views.views.UserProfileEventInt

class UserProfilePresenter(private val userProfileViewInt: UserProfileEventInt):
    UserProfilePresenterInt {

    private val userProfileRepository: UserRepository = UserRepository(this)

    override fun getUserInfo(id: String) {
//        userProfileRepository.getUserInfo(id)
    }

    override fun getEventsCreatedByUser(id: String) {
//        userProfileRepository.getEventsCreatedByUser(id)
    }

    override fun getGroupsFollowedByUser(id: String) {
//        userProfileRepository.getGroupsFollowedByUser(id)
    }

    override fun getEventsAttendedBuUser(id: String) {
//        userProfileRepository.getEventsAttendedBuUser(id)
    }

    override fun showUserInfo(user: UserModel){
        userProfileViewInt.showUserInfo(user)
    }

    override fun showEventsCreatedByUser(events: ArrayList<EventModel>) {
        userProfileViewInt.showEventsCreatedByUser(events)
    }

    override fun showGroupsFollowedByUser(groups: ArrayList<GroupModel>){
        userProfileViewInt.showGroupsFollowedByUser(groups)
    }

    override fun showEventsAttendedBuUser(events: ArrayList<EventModel>) {
        userProfileViewInt.showEventsAttendedBuUser(events)
    }
}