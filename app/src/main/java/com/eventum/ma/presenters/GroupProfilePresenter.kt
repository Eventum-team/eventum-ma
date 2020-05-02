package com.eventum.ma.presenters

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.repositories.GroupProfileRepository
import com.eventum.ma.presenters.presenters.GroupProfilePresenterInt
import com.eventum.ma.views.views.GroupProfileViewInt

class GroupProfilePresenter(var groupProfileView: GroupProfileViewInt): GroupProfilePresenterInt {

    private var groupProfileRepository: GroupProfileRepository = GroupProfileRepository(this)

    override fun showGroupProfile(group: GroupModel?) {
        groupProfileView.showGroupProfile(group)
    }

    override fun showEventsByGroup(groups: ArrayList<EventModel>?) {
        groupProfileView.showEventsByGroup(groups)
    }

    override fun getGroupProfile(id: String) {
        groupProfileRepository.getGroupProfile(id)
    }

    override fun getEventsByGroup(id: String) {
        groupProfileRepository.getEventsByGroup(id)
    }

}