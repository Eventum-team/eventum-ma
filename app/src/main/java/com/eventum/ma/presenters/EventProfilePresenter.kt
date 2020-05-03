package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.EventProfileRepository
import com.eventum.ma.presenters.presenters.EventProfilePresenterInt
import com.eventum.ma.views.views.EventProfileViewInt

class EventProfilePresenter(): ViewModel(),EventProfilePresenterInt {

    var eventDetails : MutableLiveData<EventModel> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    private var eventProfileRepository = EventProfileRepository(this)

    override fun showEventProfile(event: EventModel?) {
    }

    override fun getEventProfile(id: String) {
    }

    fun refresh(idEvent:String,idUser:String) {
        eventProfile(idEvent,idUser)
    }

    private fun eventProfile(idEvent:String, idUser:String)  {
        eventProfileRepository.getEventProf(idEvent,idUser,object : CustomCallback<EventModel> {
            override fun onSuccess(result: EventModel?) {
                eventDetails.postValue(result)
            }
            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }


}