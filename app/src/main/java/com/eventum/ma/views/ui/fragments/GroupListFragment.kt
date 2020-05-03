package com.eventum.ma.views.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventum.ma.R
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupsPresenter
import com.eventum.ma.presenters.HomePresenter
import com.eventum.ma.views.adapters.CardItemAdapter
import com.eventum.ma.views.listeners.GroupListener
import com.eventum.ma.views.ui.activities.GroupDetails
import com.eventum.ma.views.ui.activities.MainActivity
import com.eventum.ma.views.views.GroupsViewInt
import kotlinx.android.synthetic.main.fragment_group_list.*


class GroupListFragment : Fragment(),
    GroupListener{
    private lateinit var groupPresenter: GroupsPresenter
    private lateinit var groupAdapter: CardItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_group_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(topAppBarGroup)
        groupAdapter = CardItemAdapter(this)
        groupPresenter = ViewModelProviders.of(this).get(GroupsPresenter::class.java)
        groupPresenter.refresh()

        rvGroups.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        groupPresenter.listAllGroups.observe(this, Observer<List<GroupModel>> { groups ->
            groups.let {
                groupAdapter.updateData(groups)
            }
            rvGroups!!.adapter = groupAdapter
        })
        groupPresenter.isLoading.observe(this, Observer<Boolean> {
            if(it != null)
                dataLoaded()
        })
    }

    override fun onGroupClicked(group: GroupModel, position: Int) {
        val intent = Intent(view!!.context  , GroupDetails::class.java)
        intent.putExtra("GROUP", group.id_group);
        startActivity(intent)
    }

    private fun dataLoaded() {
        rlBaseGroup.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_group, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.navbar_search_group).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(view!!.context, query, Toast.LENGTH_LONG).show()
//                getGroupsByName()
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

}
