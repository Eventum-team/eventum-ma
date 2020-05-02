package com.eventum.ma.views.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eventum.ma.R

class GroupDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)
        val sessionId = intent.getStringExtra("EXTRA_SESSION_ID")
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
