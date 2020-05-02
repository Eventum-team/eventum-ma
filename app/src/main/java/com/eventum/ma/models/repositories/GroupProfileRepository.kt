package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.presenters.GroupProfilePresenterInt

class GroupProfileRepository(var groupProfilePresenter: GroupProfilePresenterInt) {

    //Logica de graphql para consumir la API
    fun getGroupProfile(){
        val g1 = GroupModel()
        g1.name = "grupazo 1"
        g1.description = "Gran descripcio 111111"
        g1.type = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val group = g1
        /*val group: ArrayList<GroupModel> = ArrayList<GroupModel>()
        group.add(g1)*/
        groupProfilePresenter.showGroupProfile(group)//(group[0])
    }

    fun getEventsByGroup(){
        val g1 = EventModel()
        g1.name = "eventazo 1"
        g1.description = "Gran descripcio 111111"
        g1.id_owner = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val g2 = EventModel()
        g2.name = "eventazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.id_owner = "typazo 2"
        g2.image = "https://homepages.cae.wisc.edu/~ece533/images/barbara.png"
        val events: ArrayList<EventModel> = ArrayList<EventModel>()
        events.add(g1)
        events.add(g2)
        groupProfilePresenter.showEventsByGroup(events)
    }

}