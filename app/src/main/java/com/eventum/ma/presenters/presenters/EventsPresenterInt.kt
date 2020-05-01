package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.EventModel

interface EventsPresenterInt {
    //View
    fun showEvents(groups: ArrayList<EventModel>?)
    fun showEventsByGroup(groups: ArrayList<EventModel>?)
    //Interactor
    fun getEvents()
    fun getEventsByName()
}