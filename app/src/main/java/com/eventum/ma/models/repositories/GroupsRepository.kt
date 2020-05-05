package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.GroupModel
import com.eventum.ma.presenters.presenters.GroupsPresenterInt
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class GroupsRepository(var groupsPresenter: GroupsPresenterInt){

    var client = OkHttpClient()
    fun allGroups(callback: CustomCallback<List<GroupModel>>){

        var url = "http://190.24.19.228:3000/graphql?query="
        url=url+"query{allGroups{id_group,id_type,type,name,description,photo}}";
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
                    if (!response.isSuccessful){
                        //callback.on(null);
                        throw IOException("Unexpected code $response")
                    }
                    else{
                        var output = JSONObject(response.body!!.string())
                        println(output)
                        if(output.has("errors")){
                            //callback(null);
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
                            callback.onSuccess(list);

                        }
                    }
                }
            }
        })

    }

    fun groupsByName(name:String,callback: CustomCallback<List<GroupModel>>){

    }

    fun groupsByType(idType:Int,callback: CustomCallback<List<GroupModel>>){

    }



}