package com.eventum.ma.presenters

import com.eventum.ma.models.models.UserModel
import com.eventum.ma.models.repositories.SignUpRepository
import com.eventum.ma.presenters.presenters.SignUpPresenterInt
import com.eventum.ma.views.views.SignUpInt

class SignUpPresenter(private var signUpView: SignUpInt  ): SignUpPresenterInt{

    private var signUpRepository: SignUpRepository = SignUpRepository(this)

    override fun createAccount(user: UserModel) {
       signUpRepository.createAccount(user)
    }

    override fun onUserCreated() {
        signUpView.onUserCreated()
    }

    override fun onCreationFailed() {
        signUpView.onCreationFailed()
    }

}