package com.eventum.ma.presenters

import com.eventum.ma.models.interactors.GroupsInteractor
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.presenters.GroupPresenterInt
import com.eventum.ma.views.views.GroupsViewInt

class GroupsPresenter(var groupsView: GroupsViewInt): GroupPresenterInt{

    private var groupsInteractor: GroupsInteractor = GroupsInteractor(this)

    override fun showGroups(groups: ArrayList<GroupModel>?) {
        groupsView.showGroups(groups)
    }

    override fun showGroupsByGroup(groups: ArrayList<GroupModel>?) {
        groupsView.showGroupsByName(groups)
    }

    override fun getGroups() {
        groupsInteractor.getGroups()
    }

    override fun getGroupsByName() {
        groupsInteractor.getGroupsByName()
    }

}