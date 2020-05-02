package com.eventum.ma.views.views

import com.eventum.ma.models.models.EventModel

interface EventProfileViewInt {
    fun showEventProfile(event: EventModel?)
    //Presenter
    fun getEventProfile()
}