package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.CommentModel
import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.presenters.presenters.EventProfilePresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject

class EventProfileRepository(var eventProfilePresenter: EventProfilePresenterInt) {

    private var client = OkHttpClient()

    fun getEventProf(idEvent: String, idUser: String, callback: CustomCallback<EventModel>) {
        println("event****************")
        println(idEvent)
        println(idUser)
        Constants.url
        var url = Constants.url
        url+= "query{\n" +
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
                "      career\n" +
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
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback.onFailed(e);
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
                            //  callback(null);
                        } else {

                            output = output.get("data") as JSONObject;
                            output = output.get("eventProfile") as JSONObject;
                            println(output);
                            val eventModel = EventModel()
                            eventModel.id_event = output.get("id").toString();
                            eventModel.owner_type = output.get("ownerType").toString();
                            eventModel.status = output.get("status").toString();
                            eventModel.event_type = output.get("eventType").toString();
                            eventModel.id_owner = output.get("ownerId").toString();
                            eventModel.name = output.get("name").toString();
                            eventModel.eventStartDate = output.get("eventStartDate").toString()
                            eventModel.eventFinishDate = output.get("eventFinishDate").toString()
                            eventModel.description = output.get("description").toString();
                            eventModel.url = output.get("url").toString();
                            eventModel.latitude = output.get("latitude").toString();
                            eventModel.longitude = output.get("longitude").toString();
                            eventModel.image = output.get("photo").toString()

                            val listUserFollowers: MutableList<UserModel> =
                                ArrayList()  //En graphql todavia nod evuelve unalista de usuarios
                            val listUserAssistant: MutableList<UserModel> = ArrayList()
                            val listUserInterested: MutableList<UserModel> = ArrayList()
                            val listComments: MutableList<CommentModel> = ArrayList()

                            var arr = output.get("comments") as JSONArray;
                            var obj: JSONObject
                            for (i in 0..arr.length() - 1) {
                                obj = arr[i] as JSONObject;
                                var commentModel: CommentModel = CommentModel();

                                commentModel.id = obj.get("id").toString()
                                commentModel.idEvent = obj.get("idEvent").toString()
                                commentModel.idUser = obj.get("idUser").toString()
                                commentModel.name = obj.get("name").toString()
                                commentModel.text = obj.get("text").toString()
                                commentModel.updated_at = obj.get("updated_at").toString()
                                commentModel.likes = obj.get("likes").toString()
                                commentModel.dislikes = obj.get("dislikes").toString()
                                commentModel.reacted = obj.get("reacted").toString()
                                listComments.add(commentModel);
                            }
                            arr = output.get("assistant") as JSONArray;
                            for (i in 0..arr.length() - 1) {
                                obj = arr[i] as JSONObject;
                                var userModel: UserModel = UserModel();
                                userModel.id_user = obj.get("id").toString()
                                userModel.name = obj.get("name").toString()
                                userModel.image = obj.get("photo").toString()
                                userModel.career = obj.get("career").toString()
                                listUserAssistant.add(userModel);
                            }

                            arr = output.get("interested") as JSONArray;
                            for (i in 0..arr.length() - 1) {
                                obj = arr[i] as JSONObject;
                                var userModel: UserModel = UserModel();
                                userModel.id_user = obj.get("id").toString()
                                userModel.name = obj.get("name").toString()
                                userModel.image = obj.get("photo").toString()

                                listUserInterested.add(userModel);
                            }
                            /*arr = output.get("followers") as JSONArray;
                            for (i in 0..arr.length()-1){
                                obj = arr[i] as JSONObject;
                                var userModel: UserModel = UserModel();
                                userModel.id_user = obj.get("id").toString()
                                userModel.name = obj.get("name").toString()
                                userModel.image = obj.get("photo").toString()

                                listUserFollowers.add(userModel);
                            }*/
                            eventModel.assistant = ArrayList(listUserAssistant)
                            eventModel.interested = ArrayList(listUserInterested)
                            eventModel.comments = ArrayList(listComments)
                            //eventModel.followers = ArrayList(listUserFollowers)

                            callback.onSuccess(eventModel);
                        }
                    }
                }
            }
        })
    }

    fun eventFollow(idEvent: String,idUser:String,callback : CustomCallback<Boolean>){

        var url = Constants.url
        url+="mutation {\n" +
                "  addUserEvent(input:{user_id:$idUser,event_id:$idEvent,assistance:true,interested:true}){\n" +
                "    message\n" +
                "    status\n" +
                "  }\n" +
                "}";
        val request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())         //ONLY FOR POST
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback.onFailed(e);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        //callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback.onSuccess(false);
                        }else {
                            callback.onSuccess(true);
                        }
                    }
                }
            }
        })
    }
    fun eventUnfollow(idEvent: String,idUser:String,callback : CustomCallback<Boolean>){

        var url = Constants.url
        url+="mutation {\n" +
                "  deleteUserEvent(userId:$idUser,eventId:$idEvent){\n" +
                "    message\n" +
                "    status\n" +
                "  }\n" +
                "}";
        val request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())         //ONLY FOR POST
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback.onFailed(e);
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        //callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            callback.onSuccess(false);
                        }else {
                            callback.onSuccess(true);
                        }
                    }
                }
            }
        })

    }
}