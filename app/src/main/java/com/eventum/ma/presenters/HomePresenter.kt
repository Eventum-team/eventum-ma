package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.HomeRepository
import com.eventum.ma.presenters.presenters.HomePresenterInt
import com.eventum.ma.views.views.HomeViewInt

class HomePresenter(): HomePresenterInt, ViewModel(){

    private var homeRepository: HomeRepository = HomeRepository(this)

    override fun showTodayEvents(events: ArrayList<EventModel>?) {
        println("*******************llega al presenter")
//        homeView.showTodayEvents(events)
    }

    override fun showOfficialEvents(events: ArrayList<EventModel>?) {
//        homeView.showOfficialEvents(events)
    }

    override fun getTodayEvents() {
        homeRepository.getTodayEvents()
    }

    override fun getOfficialEvents() {
        homeRepository.getOfficialEvents()
    }






    var listTodayEvents : MutableLiveData<List<EventModel>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    var homeRepo = HomeRepository(this)

    fun refresh() {
        todayEvents()
    }

    fun todayEvents()  {

        homeRepo.todayEvent(object : CustomCallback<List<EventModel>>{
            override fun onSuccess(result: List<EventModel>?) {
                listTodayEvents.postValue(result)
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

