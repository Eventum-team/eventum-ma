package com.eventum.ma.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.eventum.ma.R
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupsPresenter
import com.eventum.ma.views.adapters.GroupItemAdapter
import com.eventum.ma.views.listeners.GroupListener
import com.eventum.ma.views.views.GroupsViewInt
import kotlinx.android.synthetic.main.fragment_group_list.*


class GroupListFragment : Fragment(),
    GroupListener, GroupsViewInt {

    private var groupsPresenter: GroupsPresenter? = null
    private lateinit var groupAdapter: GroupItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        groupAdapter = GroupItemAdapter(this)
        groupsPresenter = GroupsPresenter(this)

        rvGroups.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }

        getGroups()
//        showGroups(groups)
    }


    override fun onConferenceClicked(group: GroupModel, position: Int) {
        val bundle = bundleOf("group" to group)
        findNavController().navigate(R.id.groupDetailsDialogFragment, bundle)
    }

    override fun showGroups(groups: ArrayList<GroupModel>?) {

        groupAdapter.updateData(groups)
        try {
            rvGroups!!.adapter = groupAdapter
            dataLoaded()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun showGroupsByName(groups: ArrayList<GroupModel>?) {

    }

    override fun getGroups() {
        groupsPresenter?.getGroups()
    }

    override fun getGroupsByName() {
        groupsPresenter?.getGroupsByName()
    }

    private fun dataLoaded() {
        rlBaseGroup.visibility = View.INVISIBLE
    }








}
