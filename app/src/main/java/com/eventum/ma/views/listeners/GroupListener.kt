package com.eventum.ma.views.listeners

import com.eventum.ma.models.models.GroupModel

interface GroupListener {
    fun onConferenceClicked(group: GroupModel, position: Int)
}