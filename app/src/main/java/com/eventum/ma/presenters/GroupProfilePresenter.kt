package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.GroupProfileRepository
import com.eventum.ma.presenters.presenters.GroupProfilePresenterInt
import com.eventum.ma.views.views.GroupProfileViewInt

class GroupProfilePresenter(): ViewModel(), GroupProfilePresenterInt {

    var eventDetails : MutableLiveData<GroupModel> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    private var eventProfileRepository = GroupProfileRepository(this)
    override fun showGroupProfile(group: GroupModel?) {
    }

    override fun showEventsByGroup(groups: ArrayList<EventModel>?) {
    }

    override fun getGroupProfile(id: String) {
    }

    override fun getEventsByGroup(id: String) {
    }

    fun refresh(idGroup:String){
        groupProfile(idGroup)
    }

    private fun groupProfile(idGroup:String)  {
        eventProfileRepository.getGroupProf(idGroup,object : CustomCallback<GroupModel> {
            override fun onSuccess(result: GroupModel?) {
                eventDetails.postValue(result)
            }
            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }




}