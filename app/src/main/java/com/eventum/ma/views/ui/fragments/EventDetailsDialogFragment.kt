package com.eventum.ma.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eventum.ma.R

/**
 * A simple [Fragment] subclass.
 */
class EventDetailsDialogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_event_details_dialog, container, false)// Inflate the layout for this fragment
    }

}
