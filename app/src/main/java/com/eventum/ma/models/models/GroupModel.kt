package com.eventum.ma.models.models

import java.io.Serializable
import java.util.*

class GroupModel: Serializable {
    lateinit var id_group: String
    lateinit var type: String
    lateinit var name: String
    lateinit var description: String
    lateinit var created_date: Date
    lateinit var contact_number: String
    lateinit var status: String
    lateinit var image: String

}
