package com.eventum.ma.views.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eventum.ma.R

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
