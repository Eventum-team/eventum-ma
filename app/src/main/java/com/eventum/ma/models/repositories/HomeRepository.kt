package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.HomePresenter
import com.eventum.ma.presenters.presenters.HomePresenterInt

class HomeRepository(var homePresenter: HomePresenterInt){
    fun getTodayEvents(){
        val g1 = EventModel()
        g1.name = "eventazo 1"
        g1.description = "Gran descripcio 111111"
        g1.id_owner = "typazo 1"
        g1.image = "https://images.unsplash.com/photo-1588285210457-8178eb4c9984?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
        val g2 = EventModel()
        g2.name = "eventazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.id_owner = "typazo 2"
        g2.image = "https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
        val events: ArrayList<EventModel> = ArrayList<EventModel>()
        events.add(g1)
        events.add(g2)
        events.add(g1)
        events.add(g2)
        homePresenter.showTodayEvents(events)
    }

    fun getOfficialEvents(){
        val g1 = EventModel()
        g1.name = "eventazo 111111"
        g1.description = "Gran descripcio 111111"
        g1.id_owner = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val g2 = EventModel()
        g2.name = "eventazo 222222"
        g2.description = "Gran descripcio 2222222"
        g2.id_owner = "typazo 2"
        g2.image = "https://homepages.cae.wisc.edu/~ece533/images/barbara.png"
        val events: ArrayList<EventModel> = ArrayList<EventModel>()
        events.add(g1)
        events.add(g2)
        homePresenter.showOfficialEvents(events)
    }
}