package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.GroupsPresenter
import com.eventum.ma.presenters.presenters.GroupsPresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class GroupsRepository(var groupsPresenter: GroupsPresenterInt){

    var client = OkHttpClient()
    fun allGroups(callback: (Array<GroupModel>?) -> Unit){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"query{allGroups{id_group,id_type,type,name,description,photo}}";
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

                        }
                    }
                }
            }
        })

    }

    fun allGroupsCallback(group: Array<GroupModel>?) {
        if (group == null) {
            println("-----------SOMETHING WRONG-----------------")
        } else {
            /*for (elem in group){
                println("Grupo: ")
                println(elem.id_group)
                println(elem.name)
                println(elem.description)
                println(elem.type)
                println(elem.image)
            }*/
            groupsPresenter.showGroups(group.toCollection(ArrayList()))
            println("-----------Todo Goood-----------------")

            // use colors as returned by API
        }
    }
    //Logica de graphql para consumir la API
    fun getGroups(){
        allGroups(this::allGroupsCallback)
    }

    fun getGroupsByName(){
        val g1 = GroupModel()
        g1.name = "prueba con busqueda"
        g1.description = "Gran descripcio 111111"
        g1.type = "typazo 1"
        g1.image = "https://images.unsplash.com/photo-1531297484001-80022131f5a1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2014&q=80"
        val g2 = GroupModel()
        g2.name = "grupazo 2"
        g2.description = "Gran descripcio 2222222"
        g2.type = "typazo 2"
        g2.image = "https://images.unsplash.com/photo-1588349779545-79444e3f673d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
        val groups: ArrayList<GroupModel> = ArrayList<GroupModel>()
        groups.add(g1)

        groupsPresenter.showGroups(groups)
    }

}