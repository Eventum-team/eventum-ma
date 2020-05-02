package com.eventum.ma.presenters

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.repositories.EventsRepository
import com.eventum.ma.presenters.presenters.EventsPresenterInt
import com.eventum.ma.views.views.EventsViewInt

class EventsPresenter(var eventsView: EventsViewInt): EventsPresenterInt{

    private var eventsRepository: EventsRepository = EventsRepository(this)

    override fun showEvents(groups: ArrayList<EventModel>?) {
        eventsView.showEvents(groups)
    }

    override fun showEventsByGroup(groups: ArrayList<EventModel>?) {
        eventsView.showEventsByName(groups)
    }

    override fun getEvents() {
//        eventsRepository.getEvents()
    }

    override fun getEventsByName() {
//        eventsRepository.getEventsByName()
    }

}