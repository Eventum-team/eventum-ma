package com.eventum.ma.models.models

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class UserModel: Serializable {
    lateinit var id_user: String
    lateinit var name: String
    lateinit var email: String
    lateinit var password: String
    var age: Int = 0
    lateinit var phone_number: String
    lateinit var career: String
    lateinit var status: String
    lateinit var image: String
    lateinit var groupsFollowing: ArrayList<GroupModel>
    lateinit var eventsCreated: ArrayList<EventModel>
    lateinit var assistanceEvents: ArrayList<EventModel>

}

