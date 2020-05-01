package com.eventum.ma.views.views

import com.eventum.ma.models.models.EventModel

interface HomeViewInt{
    fun showTodayEvents(events: ArrayList<EventModel>?)
    fun showOfficialEvents(events: ArrayList<EventModel>?)
    //Presenter
    fun getTodayEvents()

    fun getOfficialEvents()
}