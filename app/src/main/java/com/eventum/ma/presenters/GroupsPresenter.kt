package com.eventum.ma.presenters

import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.repositories.GroupsRepository
import com.eventum.ma.presenters.presenters.GroupsPresenterInt
import com.eventum.ma.views.views.GroupsViewInt

class GroupsPresenter(var groupsView: GroupsViewInt): GroupsPresenterInt{

    private var groupsRepository: GroupsRepository = GroupsRepository(this)

    override fun showGroups(groups: ArrayList<GroupModel>?) {
        groupsView.showGroups(groups)
    }

    override fun showGroupsByGroup(groups: ArrayList<GroupModel>?) {
        groupsView.showGroupsByName(groups)
    }

    override fun getGroups() {
        groupsRepository.getGroups()
    }

    override fun getGroupsByName() {
        groupsRepository.getGroupsByName()
    }

}