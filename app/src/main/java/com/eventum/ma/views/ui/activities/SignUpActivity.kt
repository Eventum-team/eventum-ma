package com.eventum.ma.views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eventum.ma.R
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.presenters.SignUpPresenter
import com.eventum.ma.presenters.presenters.SignUpPresenterInt
import com.eventum.ma.views.views.SignUpInt

class SignUpActivity : AppCompatActivity(),SignUpInt {

    private var signUpPresenter: SignUpPresenterInt? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpPresenter = SignUpPresenter(this)
        val user = UserModel()
        val registerButton = findViewById<Button>(R.id.registerButton)
        user.name = findViewById<EditText>(R.id.signUpName).text.toString()
        user.email = findViewById<EditText>(R.id.signUpEmail).text.toString()
        user.password = findViewById<EditText>(R.id.signUpPassword).text.toString()
        user.phone_number = findViewById<EditText>(R.id.signUpPhone).text.toString()
        user.status = findViewById<EditText>(R.id.signUpAge).text.toString()
        user.career = findViewById<EditText>(R.id.signUpCareer).text.toString()

        //validar campos

        registerButton.setOnClickListener {
            createAccount(user)
        }
    }

    override fun createAccount(user: UserModel) {
        user.age = 10
        signUpPresenter?.createAccount(user)
    }

    override fun onUserCreated() {
        println("userCreated")
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreationFailed() {
        println("userNOTCreated")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }



}
