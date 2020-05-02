package com.eventum.ma.controllers

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject
import kotlin.reflect.KFunction1

class GraphqlConnection {
    var client = OkHttpClient()


    fun logIn(correo: String, password: String, callback: (Array<String>?) -> Unit){

        var url = "http://190.24.19.228:3000/graphql?query=mutation {\n" +
                "  logUser(input:{username:\"$correo\",password:\"$password\"}){\n" +
                "    refresh\n" +
                "    access\n" +
                "  }\n" +
                "}";
        var request = Request.Builder()
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
                    if (!response.isSuccessful){
                        callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{

                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback(null);
                        }else {
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
    fun todayEvent(callback: (Array<EventModel>?) -> Unit){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"query {\n" +
                "  todayEvents{\n" +
                "    id\n" +
                "    ownerType\n" +
                "    status\n" +
                "    eventType\n" +
                "    ownerId\n" +
                "    name\n" +
                "    eventStartDate\n" +
                "    eventFinishDate\n" +
                "    description\n" +
                "    url\n" +
                "\t  photo\n" +
                "  }\n" +
                "}";
        var request = Request.Builder()
            .url(url)
            //.post(FormBody.Builder().build())         ONLY FOR POST
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback(null);
                        }else {
                            // NECESITO DATOS PARA PROBAR
                            callback(null);
                        }
                    }
                }
            }
        })

    }
    fun allGroups(callback: (Array<GroupModel>?) -> Unit){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"query {\n" +
                "  allGroups{\n" +
                "    id_group\n" +
                "    id_type\n" +
                "    type\n" +
                "    name\n" +
                "    description\n" +
                "    photo\n" +
                "  }\n" +
                "}";
        var request = Request.Builder()
            .url(url)
            //.post(FormBody.Builder().build())         ONLY FOR POST
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback(null);
                        }else {
                            output = output.get("data") as JSONObject;
                            var arr = output.get("allGroups") as JSONArray;
                            println(arr);
                            val list: MutableList<GroupModel> = ArrayList()
                            var obj: JSONObject
                            for (i in 0..arr.length()-1){
                                obj = arr[i] as JSONObject;
                                var gm: GroupModel = GroupModel();
                                gm.id_group = obj.get("id_group").toString()
                                gm.type = obj.get("type").toString()
                                gm.name = obj.get("name").toString()
                                gm.description = obj.get("description").toString()
                                gm.image = obj.get("photo").toString()
                                list.add(gm);
                            }
                            callback(list.toTypedArray());
                            // NECESITO DATOS PARA PROBAR

                        }
                    }
                }
            }
        })

    }

    fun allEvents(callback: (Array<EventModel>?) -> Unit){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"query {\n" +
                "  allEvents{\n" +
                "    id\n" +
                "    ownerType\n" +
                "    status\n" +
                "    eventType\n" +
                "    ownerId\n" +
                "    name\n" +
                "    description\n" +
                "    url\n" +
                "    photo\n" +
                "  }\n" +
                "}";
        var request = Request.Builder()
            .url(url)
            //.post(FormBody.Builder().build())         ONLY FOR POST
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback(null);
                        }else {
                            output = output.get("data") as JSONObject;
                            var arr = output.get("allEvents") as JSONArray;
                            println(arr);
                            val list: MutableList<EventModel> = ArrayList()
                            var obj: JSONObject
                            for (i in 0..arr.length()-1){
                                obj = arr[i] as JSONObject;
                                var em: EventModel = EventModel();
                                em.id_event = obj.get("id").toString()
                                em.owner_type = obj.get("ownerType").toString()
                                em.event_type = obj.get("eventType").toString()
                                em.id_owner = obj.get("ownerId").toString()
                                em.description = obj.get("description").toString()
                                em.name = obj.get("name").toString()
                                em.url = obj.get("url").toString()
                                em.image = obj.get("photo").toString()
                                em.status = obj.get("status").toString()
                                list.add(em);
                            }
                            callback(list.toTypedArray());
                            // NECESITO DATOS PARA PROBAR

                        }
                    }
                }
            }
        })

    }

    fun getUserProfile(idUser: Int, callback: (JSONObject?) -> Unit) {

        var url = "http://190.24.19.228:3000/graphql?query="
        url = url + "query {\n" +
                "  userProfile(userId: $idUser){\n" +
                "    id\n" +
                "    name\n" +
                "    phone_number\n" +
                "    age\n" +
                "    career\n" +
                "    status\n" +
                "    groupsFollowing{\n" +
                "      id_group\n" +
                "      id_type\n" +
                "      type\n" +
                "      name\n" +
                "      description\n" +
                "      photo\n" +
                "    }\n" +
                "    eventsCreated{\n" +
                "      id\n" +
                "      ownerType\n" +
                "      status\n" +
                "      eventType\n" +
                "      ownerId\n" +
                "      name\n" +
                "      description\n" +
                "      url\n" +
                "      photo\n" +
                "    }\n" +
                "    photo\n" +
                "  }\n" +
                "}";
        var request = Request.Builder()
            .url(url)
            //.post(FormBody.Builder().build())         ONLY FOR POST
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
                            callback(output);

                            // NECESITO DATOS PARA PROBAR

                        }
                    }
                }
            }
        })
    }
    fun getEventProfile(idEvent: Int, idUser: Int, callback: (JSONObject?) -> Unit){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"query{\n" +
                "  eventProfile(eventId:$idEvent,userId:$idUser){\n" +
                "  \tid\n" +
                "    ownerType\n" +
                "    status\n" +
                "    eventType\n" +
                "    ownerId\n" +
                "    name\n" +
                "    eventStartDate\n" +
                "    eventFinishDate\n" +
                "    description\n" +
                "    url\n" +
                "    latitude\n" +
                "    longitude\n" +
                "    comments{\n" +
                "      id\n" +
                "      idEvent\n" +
                "      idUser\n" +
                "      name\n" +
                "      text\n" +
                "      updated_at\n" +
                "      created_at\n" +
                "      likes\n" +
                "      dislikes\n" +
                "      reacted\n" +
                "    }\n" +
                "    followers\n" +
                "    assistant{\n" +
                "      id\n" +
                "      name\n" +
                "     \tphoto\n" +
                "    }\n" +
                "    interested{\n" +
                "      id\n" +
                "      name\n" +
                "      photo\n" +
                "    }\n" +
                "    photo\n" +
                "  }\n" +
                "}";
        var request = Request.Builder()
            .url(url)
            //.post(FormBody.Builder().build())         ONLY FOR POST
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback(null);
                        }else {
                            callback(output);

                            // NECESITO DATOS PARA PROBAR

                        }
                    }
                }
            }
        })
    }

    fun getGroupProfile(idGroup: Int, callback: (JSONObject?) -> Unit) {

        var url = "http://190.24.19.228:3000/graphql?query="
        url = url + "query {\n" +
                "  groupProfile(id:$idGroup){\n" +
                "    id_group\n" +
                "    id_type\n" +
                "    type\n" +
                "    name\n" +
                "    description\n" +
                "    created_date\n" +
                "    contact_number\n" +
                "    status\n" +
                "    followers\n" +
                "    events{\n" +
                "      id\n" +
                "      ownerType\n" +
                "      status\n" +
                "      eventType\n" +
                "      ownerId\n" +
                "      name\n" +
                "      description\n" +
                "      url\n" +
                "      photo\n" +
                "    }\n" +
                "    admins{\n" +
                "      id\n" +
                "      name\n" +
                "      photo\n" +
                "    }\n" +
                "    photo\n" +
                "    \n" +
                "  }\n" +
                "}";
        var request = Request.Builder()
            .url(url)
            //.post(FormBody.Builder().build())         ONLY FOR POST
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
                            callback(output);

                            // NECESITO DATOS PARA PROBAR

                        }
                    }
                }
            }
        })
    }
}