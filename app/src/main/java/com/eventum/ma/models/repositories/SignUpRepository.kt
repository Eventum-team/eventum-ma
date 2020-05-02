package com.eventum.ma.models.repositories

import com.eventum.ma.controllers.GraphqlConnection
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.presenters.presenters.SignUpPresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONObject


class SignUpRepository(var signUpPresenter: SignUpPresenterInt){
    var client = OkHttpClient()

    fun register(username: String, password: String, name: String, phoneNumber: String, age: Int, career: String, status: String, callback: (Boolean) -> Unit){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"mutation {addUser(input:{username:\"$username\",password:\"$password\",name:\"$name\",phone_number:\"$phoneNumber\",age:$age,career:\"$career\",status:\"$status\"}){message,status}}";
        var request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(false);

            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        callback(false);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback(false);
                        }else {
                            callback(true);
                        }
                    }
                }
            }
        })

    }

    fun registerCallback(salida: Boolean?){
        if(salida==false){
            signUpPresenter.onCreationFailed()
            println("-----------SOMETHING WRONG-----------------")
        }else{
            signUpPresenter.onUserCreated()
            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }

    fun createAccount(user: UserModel){

        register(user.email,user.password,user.name,user.phone_number,user.age,user.career,user.status,this::registerCallback);

    }




}