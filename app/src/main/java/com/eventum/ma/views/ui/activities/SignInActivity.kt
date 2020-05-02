package com.eventum.ma.views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eventum.ma.R
import com.eventum.ma.controllers.GraphqlConnection
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.SignInPresenter
import com.eventum.ma.views.views.SignInInt
import org.json.JSONObject


class SignInActivity : AppCompatActivity(), SignInInt {

  private var signInPresenter: SignInPresenter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//  verifySession()

    setContentView(R.layout.activity_sign_in)
    val et_email = findViewById<EditText>(R.id.signInEmail)
    val et_password = findViewById<EditText>(R.id.signInPassword)
    val signInButton = findViewById<Button>(R.id.signInButton)
    val signUpButton = findViewById<Button>(R.id.signUpButton)
    signInPresenter = SignInPresenter(this)

    signUpButton.setOnClickListener {
      val intent = Intent(this, SignUpActivity::class.java)
      startActivity(intent)
    }
    signInButton.setOnClickListener {
      val email = et_email.text.toString()
      val password = et_password.text.toString()
      verifyAccount(email, password)
    }
  }

  override fun verifyAccount(email: String, password: String) {
    signInPresenter?.verifyAccount(email, password)
  }

  override fun allowAccess() {
    val preferences =
      getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
    //Save it
    preferences.edit().putString("session", "prueba Token").apply();

    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
  }

  override fun denyAccess() {
    Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()
  }

<<<<<<< HEAD
  private fun verifySession(){
    val preferences = getSharedPreferences("com.eventum.ma", Context.MODE_PRIVATE)
    val s = preferences!!.getString("session","")
    if (s!= ""){
      allowAccess()
=======



    fun allGroupsCallback(group: Array<GroupModel>?){
        if(group == null){
            println("-----------SOMETHING WRONG-----------------")
        }else{
            for (elem in group){
                println("Grupo: ")
                println(elem.id_group)
                println(elem.name)
                println(elem.description)
                println(elem.type)
                println(elem.image)
            }
            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }

    fun allEventsCallback(event: Array<EventModel>?){
        if(event == null){
            println("-----------SOMETHING WRONG-----------------")
        }else{
            for (elem in event){
                println("Event: ")
                println(elem.id_event)
                println(elem.owner_type)
                println(elem.status)
                println(elem.event_type)
                println(elem.id_owner)
                println(elem.name)
                println(elem.description)
                println(elem.url)
                println(elem.image)
            }
            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }
    fun ProfileCallback(userProfile: JSONObject?){
        if(userProfile == null){
            println("-----------SOMETHING WRONG-----------------")
        }else{
            println(userProfile)

            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }

    override fun denyAccess() {
        //Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()


        //getGroupProfile(7,this::ProfileCallback)


>>>>>>> 5d86c8135726f5d1b3167bbfa868ba8592a9f7e4
    }
  }


}
