package com.eventum.ma.models.interactors

import com.eventum.ma.models.interactors.interactors.GroupsInteractorInt
import com.eventum.ma.models.repositories.GroupsRepository
import com.eventum.ma.presenters.GroupsPresenter

class GroupsInteractor( groupsPresenter: GroupsPresenter): GroupsInteractorInt {

    private var groupsRepository: GroupsRepository = GroupsRepository(groupsPresenter)

    override fun getGroups() {
       groupsRepository.getGroupsGraphQL()
    }

    override fun getGroupsByName() {
        groupsRepository.getGroupsByNameGraphQL()
    }


}