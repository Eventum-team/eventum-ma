package com.eventum.ma.presenters.presenters

import com.eventum.ma.models.models.UserModel

interface SignUpPresenterInt {
    fun createAccount(user: UserModel)

    fun onUserCreated()

    fun onCreationFailed()
}