package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel
import com.eventum.ma.presenters.presenters.GroupProfilePresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject

class GroupProfileRepository(var groupProfilePresenter: GroupProfilePresenterInt) {
    private var client = OkHttpClient()

    fun getGroupProf(idGroup: String, callback: CustomCallback<GroupModel>) {

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
                            //callback(null);
                        } else {
                            output = output.get("data") as JSONObject;
                            output = output.get("groupProfile") as JSONObject;
                            println(output);
                            var groupModel: GroupModel = GroupModel()
                            groupModel.id_group = output.get("id_group").toString();
                            //groupModel. = output.get("id_type").toString();
                            groupModel.type = output.get("type").toString();
                            groupModel.name = output.get("name").toString();
                            groupModel.description = output.get("description").toString()
                            //groupModel.created_date = output.get("created_date").toString()
                            groupModel.contact_number = output.get("contact_number").toString()
                            groupModel.status = output.get("status").toString()
                            //groupModel.followers = output.get("followers").toString()
                            groupModel.image = output.get("photo").toString()

                            val listEvents: MutableList<EventModel> = ArrayList()
                            val listAdmins: MutableList<UserModel> = ArrayList()

                            var arr = output.get("events") as JSONArray;
                            var obj: JSONObject
                            for (i in 0..arr.length() - 1) {
                                obj = arr[i] as JSONObject;
                                var eventModel: EventModel = EventModel();
                                eventModel.id_event = obj.get("id").toString()
                                eventModel.owner_type = obj.get("ownerType").toString()
                                eventModel.event_type = obj.get("eventType").toString()
                                eventModel.id_owner = obj.get("ownerId").toString()
                                eventModel.description = obj.get("description").toString()
                                eventModel.name = obj.get("name").toString()
                                eventModel.url = obj.get("url").toString()
                                eventModel.image = obj.get("photo").toString()
                                eventModel.status = obj.get("status").toString()
                                listEvents.add(eventModel);
                            }
                            arr = output.get("admins") as JSONArray;
                            for (i in 0..arr.length() - 1) {
                                obj = arr[i] as JSONObject;
                                var userModel: UserModel = UserModel();
                                userModel.id_user = obj.get("id").toString()
                                userModel.name = obj.get("name").toString()
                                userModel.image = obj.get("photo").toString()
                                listAdmins.add(userModel);
                            }
                            groupModel.admins = ArrayList(listAdmins)
                            groupModel.events = ArrayList(listEvents)
                            callback.onSuccess(groupModel);
                        }
                    }
                }
            }
        })
    }
/*

    fun ProfileCallback(groupModel: GroupModel?){
        if(groupModel == null){
            println("-----------SOMETHING WRONG-----------------")
        }else{
            groupProfilePresenter.showEventsByGroup(groupModel.events)
            groupProfilePresenter.showGroupProfile(groupModel)//(group[0])
            println("-----------Todo Goood-----------------")

        }
    }
    fun getGroupProfile(id: String){
        val g1 = GroupModel()
        g1.name = "grupazo 1"
        g1.description = "Gran descripcio 111111"
        g1.type = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val group = g1
        /*val group: ArrayList<GroupModel> = ArrayList<GroupModel>()
        group.add(g1)*/
        //groupProfilePresenter.showGroupProfile(group)//(group[0])
        getGroupProf(id.toInt(), this::ProfileCallback)
    }*/

    fun getEventsByGroup(id: String) {
        val g1 = EventModel()
        g1.name = "eventazo 1"
        g1.description = "Gran descripcio 111111"
        g1.id_owner = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val g2 = EventModel()
        g2.name = "eventazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.id_owner = "typazo 2"
        g2.image = "https://homepages.cae.wisc.edu/~ece533/images/barbara.png"
        val events: ArrayList<EventModel> = ArrayList<EventModel>()
        events.add(g1)
        events.add(g2)
        groupProfilePresenter.showEventsByGroup(events)
    }

}