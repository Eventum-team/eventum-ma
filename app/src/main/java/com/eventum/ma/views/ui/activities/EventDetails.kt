package com.eventum.ma.views.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.eventum.ma.R
import kotlinx.android.synthetic.main.activity_main.*

class EventDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        val event = intent.getStringExtra("EVENT")
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show()

    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }



}
