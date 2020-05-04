package com.eventum.ma.models.repositories

import com.eventum.ma.presenters.SignInPresenter
import okhttp3.*
import okio.IOException
import org.json.JSONObject

class SignInRepository(var signInPresenter: SignInPresenter) {
    private var client = OkHttpClient()


    fun verifyAccount(correo: String, password: String) {
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
//                callback.onFailed(e);
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        //callback(null);
                        throw IOException("Unexpected code $response")
                    } else {
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if (output.has("errors")) {
                            //callback(null);
                            signInPresenter.denyAccess()
                        } else {
                            output = output.get("data") as JSONObject;
                            output = output.get("logUser") as JSONObject;
                            val tokens = arrayOf(
                                output.get("refresh").toString(),
                                output.get("access").toString()
                            );
//                            callback.onSuccess(tokens);
                            signInPresenter.allowAccess(tokens[0], tokens[1])
                        }
                    }
                }
            }
        })
    }

    fun verifyToken(token: String, callback: CustomCallback<String>) {

        val url = "http://190.24.19.228:3000/graphql?query=mutation {\n" +
                "  vrfTok(input:{token:\"$token\"}) \n" +
                "}";
        val request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
//                callback.onFailed(e);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    } else {
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if (output.has("errors")) {
                            //callback(null);
                            signInPresenter.denyAccess()
                        } else {
                            output = output.get("data") as JSONObject;
                            callback.onSuccess(output.get("vrfTok").toString());
                        }
                    }
                }
            }
        })
    }


/*
    fun verifyAccount(email: String, password: String) {
        logIn(email, password, this::logInCallback)
    }
*/

}