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
        g1.image = "https://images.unsplash.com/photo-1531297484001-80022131f5a1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2014&q=80"
        val g2 = GroupModel()
        g2.name = "grupazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.type = "typazo 2"
        g2.image = "https://images.unsplash.com/photo-1588349779545-79444e3f673d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
        val groups: ArrayList<GroupModel> = ArrayList<GroupModel>()
        groups.add(g1)
        groups.add(g2)
        groups.add(g1)
        groups.add(g2)
        groups.add(g1)
        groups.add(g2)
        groups.add(g1)
        groups.add(g2)
        groups.add(g1)
        groups.add(g2)
        groupsPresenter.showGroups(groups)
    }

    fun getGroupsByName(){
        val g1 = GroupModel()
        g1.name = "prueba con busqueda"
        g1.description = "Gran descripcio 111111"
        g1.type = "typazo 1"
        g1.image = "https://images.unsplash.com/photo-1531297484001-80022131f5a1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2014&q=80"
        val g2 = GroupModel()
        g2.name = "grupazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.type = "typazo 2"
        g2.image = "https://images.unsplash.com/photo-1588349779545-79444e3f673d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
        val groups: ArrayList<GroupModel> = ArrayList<GroupModel>()
        groups.add(g1)

        groupsPresenter.showGroups(groups)
    }

}