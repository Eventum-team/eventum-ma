package com.eventum.ma.models.repositories

import java.lang.Exception

interface CustomCallback<T> {
    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)
}