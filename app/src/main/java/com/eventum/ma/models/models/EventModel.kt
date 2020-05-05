package com.eventum.ma.models.models

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class EventModel: Serializable {
    var id_event: String = ""
    var id_owner: String = ""
    var owner_type: String = ""
    var name: String = ""
    var description: String = ""
    var event_type: String = ""
    var eventStartDate: String = ""
    var eventFinishDate: String = ""
    var status: String = ""
    var url: String = ""
    var latitude: String = ""
    var longitude: String = ""
    var image: String = ""
    lateinit var followers: ArrayList<UserModel>
    lateinit var assistant: ArrayList<UserModel>
    lateinit var interested: ArrayList<UserModel>
    lateinit var comments: ArrayList<CommentModel>
}