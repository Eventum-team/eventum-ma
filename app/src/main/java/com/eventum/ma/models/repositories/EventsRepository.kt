package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.EventsPresenter
import com.eventum.ma.presenters.presenters.EventsPresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class EventsRepository(var groupsPresenter: EventsPresenterInt ){

    var client = OkHttpClient()
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



    fun allEventsCallback(event: Array<EventModel>?){
        if(event == null){
            println("-----------SOMETHING WRONG-----------------")
        }else{
            /*for (elem in event){
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
            }*/

            groupsPresenter.showEvents(event.toCollection(ArrayList()))
            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }
    //Logica de graphql para consumir la API
    fun getEvents(){
<<<<<<< HEAD
        val g1 = EventModel()
        g1.name = "eventazo 1"
        g1.description = "Gran descripcio 111111"
        g1.id_owner = "typazo 1"
        g1.image = "https://images.unsplash.com/photo-1588285210457-8178eb4c9984?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
        val g2 = EventModel()
        g2.name = "eventazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.id_owner = "typazo 2"
        g2.image = "https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
        val events: ArrayList<EventModel> = ArrayList<EventModel>()
        events.add(g1)
        events.add(g2)
        events.add(g1)
        events.add(g2)
        events.add(g1)
        events.add(g2)
        events.add(g1)
        events.add(g2)
        groupsPresenter.showEvents(events)
=======
        allEvents(this::allEventsCallback)
>>>>>>> 5d86c8135726f5d1b3167bbfa868ba8592a9f7e4
    }

    fun getEventsByName(){

    }




}