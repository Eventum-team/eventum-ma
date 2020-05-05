package com.eventum.ma.views.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.telecom.Conference
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.eventum.ma.R
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupsPresenter
import com.eventum.ma.views.adapters.CardItemAdapter
import com.eventum.ma.views.listeners.GroupListener
import com.eventum.ma.views.ui.activities.GroupDetails
import kotlinx.android.synthetic.main.fragment_group_list.*


class GroupCategoryFragment : Fragment(),
    GroupListener {
    private lateinit var groupPresenter: GroupsPresenter
    private lateinit var groupAdapter: CardItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments?.getSerializable("type")
        var groupId = 0
        when (type) {
            "SPORTS" -> groupId = 1
            "CULTURE" -> groupId = 1
            "OTHERS" -> groupId = 1
            "RESEARCH" -> groupId = 1
            "STUDY" -> groupId = 1

        }

        groupAdapter = CardItemAdapter(this)
        groupPresenter = ViewModelProviders.of(this).get(GroupsPresenter::class.java)
        groupPresenter.refreshGroupsByType(groupId)

        rvGroups.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        groupPresenter.groups.observe(this, Observer<List<GroupModel>> { groups ->
            groups.let {
                groupAdapter.updateData(groups)
            }
            rvGroups!!.adapter = groupAdapter
            dataLoaded()
        })
        groupPresenter.isLoading.observe(this, Observer<Boolean> {
            if (it != null)
                dataLoaded()
        })
    }

    override fun onGroupClicked(group: GroupModel, position: Int) {
        val intent = Intent(view!!.context, GroupDetails::class.java)
        intent.putExtra("GROUP", group.id_group);
        startActivity(intent)
    }

    private fun dataLoaded() {
        rlBaseGroup.visibility = View.INVISIBLE
    }

}
