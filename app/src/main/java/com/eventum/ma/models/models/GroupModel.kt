package com.eventum.ma.models.models

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class GroupModel: Serializable {
    lateinit var id_group: String
    lateinit var type: String
    lateinit var name: String
    lateinit var description: String
    lateinit var created_date: Date
    lateinit var contact_number: String
    var followers: Int = 0
    lateinit var status: String
    lateinit var image: String
    lateinit var events: ArrayList<EventModel>
    lateinit var admins: ArrayList<UserModel>

}
