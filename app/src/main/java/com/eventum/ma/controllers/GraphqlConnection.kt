package com.eventum.ma.controllers

import okhttp3.*
import okio.IOException

class GraphqlConnection {
    var client = OkHttpClient()
    var url = "http://190.24.19.228:3000/graphql"
    fun getUserProfile(userId: Int){
        var request = Request.Builder()
            .url(url)
            .build()
        println("----------------Working-------------------")


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())
                }
            }
        })
    }
}