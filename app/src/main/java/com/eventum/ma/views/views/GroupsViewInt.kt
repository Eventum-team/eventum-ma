package com.eventum.ma.views.views

import com.eventum.ma.models.models.GroupModel

interface GroupsViewInt {
    //View
    fun showGroups(groups: ArrayList<GroupModel>?)
    fun showGroupsByName(groups: ArrayList<GroupModel>?)
    //Presenter
    fun getGroups()

    fun getGroupsByName()

}