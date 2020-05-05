package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.presenters.EventsPresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class EventsRepository(var groupsPresenter: EventsPresenterInt ){

    var client = OkHttpClient()
    fun allEvents(callback: CustomCallback<List<EventModel>>){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"query {\n" +
                "  allEvents{\n" +
                "    id\n" +
                "    ownerType\n" +
                "    eventStartDate\n"+
                "    status\n" +
                "    eventType\n" +
                "    ownerId\n" +
                "    name\n" +
                "    description\n" +
                "    url\n" +
                "    photo\n" +
                "  }\n" +
                "}";
        val request = Request.Builder()
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
                    if (!response.isSuccessful){
                        //callback(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            //callback(null);
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
                                em.eventStartDate = obj.get("eventStartDate").toString()
                                em.id_owner = obj.get("ownerId").toString()
                                em.description = obj.get("description").toString()
                                em.name = obj.get("name").toString()
                                em.url = obj.get("url").toString()
                                em.image = obj.get("photo").toString()
                                em.status = obj.get("status").toString()
                                list.add(em);
                            }
                            callback.onSuccess(list);
                            // NECESITO DATOS PARA PROBAR

                        }
                    }
                }
            }
        })

    }


/*
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
            println("-----------Todo Goood all events-----------------")

            // use colors as returned by API
        }
    }
    //Logica de graphql para consumir la API
    fun getEvents(){

        allEvents(this::allEventsCallback)
    }

    fun getEventsByName(){

    }

*/


}