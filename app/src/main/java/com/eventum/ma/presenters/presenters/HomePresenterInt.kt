package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.EventModel

interface HomePresenterInt {
    // View
    fun showTodayEvents(events: ArrayList<EventModel>?)
    fun showOfficialEvents(events: ArrayList<EventModel>?)
    //Interactor
    fun getTodayEvents()
    fun getOfficialEvents()
}