package com.eventum.ma.views.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventum.ma.R
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.HomePresenter
import com.eventum.ma.views.adapters.EventsHorizontalItemsAdapter
import com.eventum.ma.views.listeners.EventListener
import com.eventum.ma.views.ui.activities.EventDetails

import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), EventListener {

    private lateinit var homePresenter: HomePresenter
    private lateinit var todayEventsAdapter: EventsHorizontalItemsAdapter
    private lateinit var officialEventsAdapter: EventsHorizontalItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sportsCategoryButton = view.findViewById<Button>(R.id.home_sports)
        val cultureCategoryButton = view.findViewById<Button>(R.id.home_culture)
        val othersCategoryButton = view.findViewById<Button>(R.id.home_others)
        val researchCategoryButton = view.findViewById<Button>(R.id.home_research)
        val studyCategoryButton = view.findViewById<Button>(R.id.home_study)

        sportsCategoryButton.setOnClickListener {
            val bundle = bundleOf("type" to "SPORTS")
                    findNavController().navigate(R.id.groupCategoryFragment,bundle)
        }
        cultureCategoryButton.setOnClickListener {
            val bundle = bundleOf("type" to "CULTURE")
            findNavController().navigate(R.id.groupCategoryFragment ,bundle)
        }
        othersCategoryButton.setOnClickListener {
            val bundle = bundleOf("type" to "OTHERS")
            findNavController().navigate(R.id.groupCategoryFragment ,bundle)
        }
        researchCategoryButton.setOnClickListener {
            val bundle = bundleOf("type" to "RESEARCH")
            findNavController().navigate(R.id.groupCategoryFragment ,bundle)
        }
        studyCategoryButton.setOnClickListener {
            val bundle = bundleOf("type" to "OTHERS")
            findNavController().navigate(R.id.groupCategoryFragment ,bundle)
        }


        todayEventsAdapter = EventsHorizontalItemsAdapter(this)
        homePresenter = ViewModelProviders.of(this).get(HomePresenter::class.java)
        homePresenter.refresh()

        rvTodayEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvOfficialEvents.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        homePresenter.listTodayEvents.observe(this, Observer<List<EventModel>> { events ->
            events.let { todayEventsAdapter.updateEvents(events) }
            rvTodayEvents!!.adapter = todayEventsAdapter
            //temporal assignment
            rvOfficialEvents!!.adapter = todayEventsAdapter
            dataLoaded()
        })

    }

    override fun onEventClicked(event: EventModel, position: Int) {
        val intent = Intent(view!!.context, EventDetails::class.java)
        intent.putExtra("EVENT", event.id_event);
        startActivity(intent)
    }


    private fun dataLoaded() {
        loadingHome.visibility = View.VISIBLE
        loadingHome2.visibility = View.INVISIBLE
    }


}
