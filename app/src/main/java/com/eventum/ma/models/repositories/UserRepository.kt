package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.models.models.UserModel

import com.eventum.ma.presenters.presenters.UserProfilePresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class UserRepository(private val userProfilePresenter: UserProfilePresenterInt) {
    var client = OkHttpClient()

    fun getUserProfile(idUser: Int, callback: CustomCallback<UserModel>) {

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
                            output = output.get("userProfile") as JSONObject;
                            println(output);
                            val listEventCreated: MutableList<EventModel> = ArrayList()
                            val listGroupFollowing: MutableList<GroupModel> = ArrayList()
                            val listEventAssist: MutableList<EventModel> = ArrayList()
                            var user: UserModel = UserModel()
                            user.id_user = output.get("id").toString();
                            user.name = output.get("name").toString();
                            user.phone_number = output.get("phone_number").toString();
                            user.career = output.get("career").toString();
                            user.status = output.get("status").toString();
                            user.image = output.get("photo").toString();
                            user.age = output.get("age").toString().toInt();
                            var arr = output.get("groupsFollowing") as JSONArray;
                            var obj: JSONObject
                            for (i in 0..arr.length() - 1) {
                                obj = arr[i] as JSONObject;
                                var gm: GroupModel = GroupModel();
                                gm.id_group = obj.get("id_group").toString()
                                gm.type = obj.get("type").toString()
                                gm.name = obj.get("name").toString()
                                gm.description = obj.get("description").toString()
                                gm.image = obj.get("photo").toString()
                                listGroupFollowing.add(gm);
                            }
                            arr = output.get("eventsCreated") as JSONArray;
                            for (i in 0..arr.length() - 1) {
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
                                listEventCreated.add(em);
                            }
                            //arr = output.get("eventsAssistance") as JSONArray;
                            user.groupsFollowing = ArrayList(listGroupFollowing)
                            user.eventsCreated = ArrayList(listEventCreated)
                            callback.onSuccess(user);
                        }
                    }
                }
            }
        })
    }
}

/*
    fun ProfileCallback(userProfile: UserModel?){
        if(userProfile == null){
            println("-----------SOMETHING WRONG-----------------")
        }else{
            userProfilePresenter.showUserInfo(userProfile)

            userProfilePresenter.showEventsCreatedByUser(userProfile.eventsCreated)
            userProfilePresenter.showGroupsFollowedByUser(userProfile.groupsFollowing)
            userProfilePresenter.showEventsAttendedBuUser(userProfile.assistanceEvents)


            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }


    //Logica de graphql para consumir la API
    fun getGroupsGraphQL() {

    }
    fun getUserInfo(id: String){
        getUserProfile(id.toInt(), this::ProfileCallback)


    }

    fun getEventsCreatedByUser(id: String){
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
        //userProfilePresenter.showEventsCreatedByUser(events)

    }

    fun getGroupsFollowedByUser(id: String){
        val g1 = GroupModel()
        g1.name = "grupazo 1"
        g1.description = "Gran descripcio 111111"
        g1.type = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val g2 = GroupModel()
        g2.name = "grupazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.type = "typazo 2"
        g2.image = "https://homepages.cae.wisc.edu/~ece533/images/barbara.png"
        val groups: ArrayList<GroupModel> = ArrayList<GroupModel>()
        groups.add(g1)
        groups.add(g2)
        //userProfilePresenter.showGroupsFollowedByUser(groups)
    }

    fun getEventsAttendedBuUser(id: String){
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
        //userProfilePresenter.showEventsAttendedBuUser(events)
    }*/