package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.GroupModel

interface GroupsPresenterInt {
    //View
    fun showGroups(groups: ArrayList<GroupModel>?)
    fun showGroupsByGroup(groups: ArrayList<GroupModel>?)
    //Interactor
    fun getGroups()
    fun getGroupsByName()
}