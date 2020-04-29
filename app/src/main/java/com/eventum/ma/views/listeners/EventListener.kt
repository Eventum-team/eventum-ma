package com.eventum.ma.views.listeners

import com.eventum.ma.models.models.EventModel

interface EventListener {
    fun onEventClicked(conference: EventModel, position: Int)
}