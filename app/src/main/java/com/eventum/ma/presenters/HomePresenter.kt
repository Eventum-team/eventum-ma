package com.eventum.ma.presenters

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.repositories.HomeRepository
import com.eventum.ma.presenters.presenters.HomePresenterInt
import com.eventum.ma.views.views.HomeViewInt

class HomePresenter(var homeView: HomeViewInt): HomePresenterInt{

    private var homeRepository: HomeRepository = HomeRepository(this)

    override fun showTodayEvents(groups: ArrayList<EventModel>?) {
        homeView.showTodayEvents(groups)
    }

    override fun showOfficialEvents(groups: ArrayList<EventModel>?) {
        homeView.showOfficialEvents(groups)
    }

    override fun getTodayEvents() {
        homeRepository.getTodayEvents()
    }

    override fun getOfficialEvents() {
        homeRepository.getOfficialEvents()
    }

}

