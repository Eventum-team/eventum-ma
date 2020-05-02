package com.eventum.ma.presenters

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.repositories.EventProfileRepository
import com.eventum.ma.presenters.presenters.EventProfilePresenterInt
import com.eventum.ma.views.views.EventProfileViewInt

class EventProfilePresenter(var eventProfileView: EventProfileViewInt): EventProfilePresenterInt {

    private var eventProfileRepository: EventProfileRepository = EventProfileRepository(this)

    override fun showEventProfile(event: EventModel?) {
        eventProfileView.showEventProfile(event)
    }

    override fun getEventProfile() {
        eventProfileRepository.getEventProfile()
    }

}