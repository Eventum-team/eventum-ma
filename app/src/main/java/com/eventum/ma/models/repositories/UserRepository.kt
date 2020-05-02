package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.presenters.presenters.UserProfilePresenterInt


class UserRepository(private val userProfilePresenter: UserProfilePresenterInt) {

    fun getUserInfo(id: String){
        val u = UserModel()
        u.name = "Juan Diego"
        u.image = "https://picsum.photos/seed/picsum/200/300"
        u.career = "Ingenieria de Sistemmas"
        u.status = "Status Activo"
        u.age = "19"
        userProfilePresenter.showUserInfo(u)
    }

    fun getEventsCreatedByUser(id: String){
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
        userProfilePresenter.showEventsCreatedByUser(events)

    }

    fun getGroupsFollowedByUser(id: String){
        val g1 = GroupModel()
        g1.name = "grupazo 1"
        g1.description = "Gran descripcio 111111"
        g1.type = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val g2 = GroupModel()
        g2.name = "grupazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.type = "typazo 2"
        g2.image = "https://homepages.cae.wisc.edu/~ece533/images/barbara.png"
        val groups: ArrayList<GroupModel> = ArrayList<GroupModel>()
        groups.add(g1)
        groups.add(g2)
        userProfilePresenter.showGroupsFollowedByUser(groups)
    }

    fun getEventsAttendedBuUser(id: String){
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
        userProfilePresenter.showEventsAttendedBuUser(events)
    }
}