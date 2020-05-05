package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.GroupsRepository
import com.eventum.ma.presenters.presenters.GroupsPresenterInt
import com.eventum.ma.views.views.GroupsViewInt

class GroupsPresenter: ViewModel(), GroupsPresenterInt{

    var groups : MutableLiveData<List<GroupModel>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    private var groupsRepository = GroupsRepository(this)

    override fun showGroups(groups: ArrayList<GroupModel>?) {
    }

    override fun showGroupsByGroup(groups: ArrayList<GroupModel>?) {
    }

    override fun getGroups() {
    }

    override fun getGroupsByName() {
    }

    fun refresh() {
        allGroups()
    }

    private fun allGroups()  {
        groupsRepository.allGroups(object : CustomCallback<List<GroupModel>> {
            override fun onSuccess(result: List<GroupModel>?) {
                groups.postValue(result)
            }
            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }

    fun refreshGroupsByName(name:String){
        groupsByName(name)
    }
    private fun groupsByName(name:String)  {
        groupsRepository.groupsByName(name,object : CustomCallback<List<GroupModel>> {
            override fun onSuccess(result: List<GroupModel>?) {
                groups.postValue(result)
                isLoading.postValue(false)
            }
            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }

    fun refreshGroupsByType(idType: Int){
        groupsRepository.groupsByType(idType,object : CustomCallback<List<GroupModel>> {
            override fun onSuccess(result: List<GroupModel>?) {
                groups.postValue(result)
                isLoading.postValue(false)
            }
            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }
}