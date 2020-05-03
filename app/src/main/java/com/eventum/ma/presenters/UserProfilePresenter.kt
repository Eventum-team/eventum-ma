package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.UserRepository
import com.eventum.ma.presenters.presenters.UserProfilePresenterInt
import com.eventum.ma.views.views.EventsViewInt
import com.eventum.ma.views.views.UserProfileEventInt

class UserProfilePresenter(): UserProfileEventInt, ViewModel(){

    private val userProfileRepository: UserRepository = UserRepository()

    override fun getUserInfo(id: String) {
//        userProfileRepository.getUserInfo(id)
    }


    override fun showUserInfo(user: UserModel){
//        userProfileViewInt.showUserInfo(user)
    }

    override fun showEventsCreatedByUser(events: ArrayList<EventModel>) {
//        userProfileViewInt.showEventsCreatedByUser(events)
    }

    override fun showGroupsFollowedByUser(groups: ArrayList<GroupModel>){
 //       userProfileViewInt.showGroupsFollowedByUser(groups)
    }

    override fun showEventsAttendedBuUser(events: ArrayList<EventModel>) {
 //       userProfileViewInt.showEventsAttendedBuUser(events)
    }


    var userModel : MutableLiveData<UserModel> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    var userRepo = UserRepository()

    fun refresh(iduser: String) {
        getUserProfile(iduser)
    }

    fun getUserProfile(iduser: String)  {

        userRepo.getUserProfile(iduser, object : CustomCallback<UserModel>{
            override fun onSuccess(result: UserModel?) {
                userModel.postValue(result)
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