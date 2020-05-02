package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.EventModel
import com.eventum.ma.presenters.HomePresenter
import com.eventum.ma.presenters.presenters.HomePresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeRepository(var homePresenter: HomePresenterInt){

    var client = OkHttpClient()

    fun fromISO8601UTC(dateStr: String?): Date {
        val tz: TimeZone = TimeZone.getTimeZone("UTC")
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
        df.setTimeZone(tz)
        try {
            return df.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Date()
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
                            output = output.get("data") as JSONObject;
                            var arr = output.get("todayEvents") as JSONArray;
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
                                em.image = obj.get("url").toString()
                                em.status = obj.get("status").toString()
                                em.eventStartDate = fromISO8601UTC(obj.get("eventStartDate").toString())
                                em.eventFinishDate = fromISO8601UTC(obj.get("eventFinishDate").toString())
                                list.add(em);
                            }
                            callback(list.toTypedArray());
                            // NECESITO DATOS PARA PROBAR
                            callback(null);
                        }
                    }
                }
            }
        })

    }


    fun todayEventCallback(event: Array<EventModel>?){
        if(event == null){
            println("-----------SOMETHING WRONG-----------------")
        }else{

            homePresenter.showTodayEvents(event.toCollection(ArrayList()))

            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }


    fun getTodayEvents(){
        todayEvent(this::todayEventCallback)
    }

    fun getOfficialEvents(){
        val g1 = EventModel()
        g1.name = "eventazo 111111"
        g1.description = "Gran descripcio 111111"
        g1.id_owner = "typazo 1"
        g1.image = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val g2 = EventModel()
        g2.name = "eventazo 222222"
        g2.description = "Gran descripcio 2222222"
        g2.id_owner = "typazo 2"
        g2.image = "https://homepages.cae.wisc.edu/~ece533/images/barbara.png"
        val events: ArrayList<EventModel> = ArrayList<EventModel>()
        events.add(g1)
        events.add(g2)
        homePresenter.showOfficialEvents(events)
    }
}