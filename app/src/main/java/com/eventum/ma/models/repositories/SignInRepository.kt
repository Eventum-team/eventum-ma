package com.eventum.ma.models.repositories

import com.eventum.ma.presenters.SignInPresenter
import okhttp3.*
import okio.IOException
import org.json.JSONObject

class SignInRepository(var signInPresenter: SignInPresenter) {
    private var client = OkHttpClient()


    private fun logIn(correo: String, password: String, callback: (Array<String>?) -> Unit) {
        val url = "http://190.24.19.228:3000/graphql?query=mutation {\n" +
                "  logUser(input:{username:\"$correo\",password:\"$password\"}){\n" +
                "    refresh\n" +
                "    access\n" +
                "  }\n" +
                "}";
        val request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        callback(null);
                        throw IOException("Unexpected code $response")
                    } else {
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if (output.has("errors")) {
                            callback(null);
                        } else {
                            output = output.get("data") as JSONObject;
                            output = output.get("logUser") as JSONObject;
                            val finalData = arrayOf(
                                output.get("refresh").toString(),
                                output.get("access").toString()
                            );
                            callback(finalData);
                        }
                    }
                }
            }
        })
    }

    private fun logInCallback(salida: Array<String>?) {
        if (salida == null) {
            signInPresenter.denyAccess()
        } else {
            signInPresenter.allowAccess(salida[0],salida[1])
            println(salida[0])
            println(salida[1])
            // use colors as returned by API
        }
    }


    fun verifyAccount(email: String, password: String) {
        logIn(email, password, this::logInCallback)
    }


}