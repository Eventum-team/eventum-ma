package com.eventum.ma.views.views

import com.eventum.ma.models.models.EventModel

interface EventsViewInt {
    fun showEvents(events: ArrayList<EventModel>?)
    fun showEventsByName(events: ArrayList<EventModel>?)
    //Presenter
    fun getEvents()

    fun getEventsByName()
}