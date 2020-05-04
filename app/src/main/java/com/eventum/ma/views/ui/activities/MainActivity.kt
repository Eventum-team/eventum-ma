package com.eventum.ma.views.ui.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.eventum.ma.R
import com.eventum.ma.presenters.SignInPresenter
import com.eventum.ma.views.views.SignInInt
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SignInInt {

    private lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configNav()
        signInPresenter = SignInPresenter(this)
        val preferences = getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
        val access = preferences!!.getString("accessToken", "")
        if (access != null) {
            signInPresenter.verifyToken(access)
        }
        verifyToken(access)
    }

    private fun configNav() {
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent))
    }

    override fun verifyAccount(email: String, password: String) {
    }

    override fun verifyToken(token: String?) {
        signInPresenter.tokenAccess.observe(this, Observer { userID ->
            println("********************")
            println(userID)
            println("********************")
            val preferences = getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
            preferences.edit().putString("userID", userID).apply();
            userID.let {
                val preferences = getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
                preferences.edit().putString("userID", userID).apply();
            }
        })
    }

    override fun allowAccess(accessToken: String, refreshToken: String) {
    }

    override fun denyAccess() {
    }

}
