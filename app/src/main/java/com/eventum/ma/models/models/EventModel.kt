package com.eventum.ma.models.models

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class EventModel: Serializable {
    lateinit var id_event: String
    lateinit var id_owner: String
    lateinit var owner_type: String
    lateinit var name: String
    lateinit var description: String
    lateinit var event_type: String
    lateinit var eventStartDate: Date
    lateinit var eventFinishDate: Date
    lateinit var status: String
    lateinit var url: String
    lateinit var latitude: String
    lateinit var longitude: String
    lateinit var image: String
    lateinit var followers: ArrayList<UserModel>
    lateinit var assistant: ArrayList<UserModel>
    lateinit var interested: ArrayList<UserModel>
}