package com.example.sample.network

import java.lang.Exception

sealed class ApiResult<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>() : ApiResult<T>()

    class Success<T>(data: T) : ApiResult<T>(data)

    class Error<T>(message: String) : ApiResult<T>(message = message)
}

