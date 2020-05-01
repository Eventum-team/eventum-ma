package com.eventum.ma.views.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.eventum.ma.R
import com.eventum.ma.presenters.SignInPresenter
import com.eventum.ma.presenters.presenters.SignInPresenterInt
import com.eventum.ma.views.views.SignInInt

class SignInActivity : AppCompatActivity(),SignInInt {

     var signInPresenter: SignInPresenter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        var et_email = findViewById<EditText>(R.id.signInEmail)
        var et_password = findViewById<EditText>(R.id.signInPassword)
        var signInButton = findViewById<Button>(R.id.signInButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signInPresenter = SignInPresenter(this)

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        signInButton.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            verifyAccount(email ,password)
        }
    }

    override fun verifyAccount(email: String, password: String) {
        signInPresenter?.verifyAccount(email,password)
    }

    override fun allowAccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun denyAccess() {
        Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()
    }


}
