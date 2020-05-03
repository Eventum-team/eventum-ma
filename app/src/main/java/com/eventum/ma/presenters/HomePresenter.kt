package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.HomeRepository
import com.eventum.ma.presenters.presenters.HomePresenterInt
import com.eventum.ma.views.views.HomeViewInt

class HomePresenter(): HomePresenterInt, ViewModel(){

    var listTodayEvents : MutableLiveData<List<EventModel>> = MutableLiveData()
    private var homeRepository = HomeRepository(this)

    override fun showTodayEvents(events: ArrayList<EventModel>?) {
    }

    override fun showOfficialEvents(events: ArrayList<EventModel>?) {
    }

    override fun getTodayEvents() {
    }

    override fun getOfficialEvents() {
    }

    fun refresh() {
        todayEvents()
    }

    private fun todayEvents()  {
        homeRepository.todayEvent(object : CustomCallback<List<EventModel>>{
            override fun onSuccess(result: List<EventModel>?) {
                listTodayEvents.postValue(result)
            }
            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }


}

