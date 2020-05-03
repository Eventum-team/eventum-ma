package com.eventum.ma.models.models

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class UserModel: Serializable {
    var id_user: String = ""
    var name: String = ""
     var email: String = ""
    var password: String = ""
    var age: Int = 0
    var phone_number: String = ""
    var career: String = ""
    var status: String = ""
    var image: String = ""
    lateinit var groupsFollowing: ArrayList<GroupModel>
    lateinit var eventsCreated: ArrayList<EventModel>
    lateinit var assistanceEvents: ArrayList<EventModel>

}

