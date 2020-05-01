package com.eventum.ma.models.models

import java.io.Serializable
import java.util.*

class UserModel: Serializable {
    lateinit var id_user: String
    lateinit var name: String
    lateinit var email: String
    lateinit var password: String
    lateinit var age: String
    lateinit var career: String
    lateinit var status: String
}