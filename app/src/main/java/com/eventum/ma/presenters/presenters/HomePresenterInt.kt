package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.EventModel

interface HomePresenterInt {
    // View
    fun showTodayEvents(groups: ArrayList<EventModel>?)
    fun showOfficialEvents(groups: ArrayList<EventModel>?)
    //Interactor
    fun getTodayEvents()
    fun getOfficialEvents()
}