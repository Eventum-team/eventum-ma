package com.eventum.ma.views.listeners

import com.eventum.ma.models.models.GroupModel

interface GroupListener {
    fun onGroupClicked(group: GroupModel, position: Int)
}