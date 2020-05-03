package com.eventum.ma.views.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.eventum.ma.R
import com.eventum.ma.presenters.SignInPresenter
import com.eventum.ma.views.views.SignInInt


class SignInActivity : AppCompatActivity(), SignInInt {

    private lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verifySession()
        setContentView(R.layout.activity_sign_in)
        val etEmail = findViewById<EditText>(R.id.signInEmail)
        val etPassword = findViewById<EditText>(R.id.signInPassword)
        val signInButton = findViewById<Button>(R.id.signInButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        signInPresenter = SignInPresenter(this)

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            verifyAccount(email, password)
        }

    }

    override fun verifyAccount(email: String, password: String) {
        signInPresenter.verifyAccount(email, password)
    }

    override fun verifyToken(token: String?) {
        signInPresenter.tokenAccess.observe(this, Observer { token ->
            token.let {
                val preferences = getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
                preferences.edit().putString("accessToken", token).apply();
            }
        })
    }

    override fun allowAccess(accessToken:String,refreshToken:String) {
        val preferences = getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
        preferences.edit().putString("refreshToken", refreshToken).apply();
        preferences.edit().putString("accessToken", accessToken).apply();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun denyAccess() {

    }

    private fun verifySession() {
        val preferences = getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
        val access = preferences!!.getString("accessToken", "")

        verifyToken(access)

        if (access != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}



