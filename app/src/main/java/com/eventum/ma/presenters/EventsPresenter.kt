package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.EventsRepository
import com.eventum.ma.models.repositories.GroupsRepository
import com.eventum.ma.presenters.presenters.EventsPresenterInt
import com.eventum.ma.views.views.EventsViewInt

class EventsPresenter() : ViewModel(), EventsPresenterInt {

    var listAllEvents: MutableLiveData<List<EventModel>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    private var eventsRepository = EventsRepository(this)

    override fun showEvents(groups: ArrayList<EventModel>?) {
    }

    override fun showEventsByGroup(groups: ArrayList<EventModel>?) {
    }

    override fun getEvents() {
    }

    override fun getEventsByName() {
    }

    fun refresh() {
        allGroups()
    }

    private fun allGroups() {
        eventsRepository.allEvents(object : CustomCallback<List<EventModel>> {
            override fun onSuccess(result: List<EventModel>?) {
                listAllEvents.postValue(result)
            }

            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }

}