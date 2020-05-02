package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.presenters.EventProfilePresenterInt

class EventProfileRepository(var eventProfilePresenter: EventProfilePresenterInt) {

    //Logica de graphql para consumir la API

    fun getEventProfile(id: String){
        val g1 = EventModel()
        g1.name = "eventazo 1"
        g1.description = "Gran descripcio 111111"
        g1.event_type = "type"
        g1.id_owner = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val event = g1
        eventProfilePresenter.showEventProfile(event)
    }


}