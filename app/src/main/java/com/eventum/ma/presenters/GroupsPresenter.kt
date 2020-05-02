package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.GroupsRepository
import com.eventum.ma.models.repositories.HomeRepository
import com.eventum.ma.presenters.presenters.GroupsPresenterInt
import com.eventum.ma.views.views.GroupsViewInt

class GroupsPresenter(): ViewModel(), GroupsPresenterInt{

    private var groupsRepository: GroupsRepository = GroupsRepository(this)

    override fun showGroups(groups: ArrayList<GroupModel>?) {
//        groupsView.showGroups(groups)
    }

    override fun showGroupsByGroup(groups: ArrayList<GroupModel>?) {
//        groupsView.showGroupsByName(groups)
    }

    override fun getGroups() {
        groupsRepository.getGroups()
    }

    override fun getGroupsByName() {
        groupsRepository.getGroupsByName()
    }









    var listAllGroups : MutableLiveData<List<GroupModel>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    var groupRepo = GroupsRepository(this)

    fun refresh() {
        allGroups()
    }

    fun allGroups()  {

        groupRepo.allGroups(object : CustomCallback<List<GroupModel>> {
            override fun onSuccess(result: List<GroupModel>?) {
                listAllGroups.postValue(result)
            }

            override fun onFailed(exception: java.lang.Exception) {
                processFinished()
            }

        })

    }

    private fun processFinished() {
        isLoading.value = true
    }



}