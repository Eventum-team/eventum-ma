package com.eventum.ma.presenters

import com.eventum.ma.models.repositories.SignInRepository
import com.eventum.ma.presenters.presenters.SignInPresenterInt
import com.eventum.ma.views.views.EventsViewInt
import com.eventum.ma.views.views.SignInInt

class SignInPresenter (var signInView: SignInInt): SignInPresenterInt {

    private val signInRepository: SignInRepository = SignInRepository(this)

    override fun verifyAccount(email: String, password: String) {
        signInRepository.verifyAccount(email,password)
    }

    override fun allowAccess(accessToken:String,refreshToken:String) {
        signInView.allowAccess(accessToken,refreshToken)

    }

    override fun denyAccess() {
        signInView.denyAccess()
    }

}