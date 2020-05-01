package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupsPresenter
import com.eventum.ma.presenters.presenters.GroupsPresenterInt


class GroupsRepository(var groupsPresenter: GroupsPresenterInt){

    //Logica de graphql para consumir la API
    fun getGroups(){
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
        groupsPresenter.showGroups(groups)
    }

    fun getGroupsByName(){

    }

}