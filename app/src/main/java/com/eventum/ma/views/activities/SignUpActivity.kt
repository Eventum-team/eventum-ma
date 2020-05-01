package com.eventum.ma.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eventum.ma.R
import com.eventum.ma.controllers.GraphqlConnection

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }
    fun clickbut(){
        println("----------working here---------------")
        val graph = GraphqlConnection();

        graph.getUserProfile(11);
    }
}
