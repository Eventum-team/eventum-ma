package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.EventModel

interface EventProfilePresenterInt {
    fun showEventProfile(event: EventModel?)
    //Presenter
    fun getEventProfile(id: String)
}