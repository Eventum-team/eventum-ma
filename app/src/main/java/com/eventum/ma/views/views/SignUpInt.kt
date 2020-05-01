package com.eventum.ma.views.views

import com.eventum.ma.models.models.UserModel

interface SignUpInt {
    fun createAccount(user: UserModel)

    fun onUserCreated()

    fun onCreationFailed()
}