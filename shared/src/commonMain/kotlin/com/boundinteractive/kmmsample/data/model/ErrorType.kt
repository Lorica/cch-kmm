package com.boundinteractive.kmmsample.data.model

import io.ktor.client.plugins.ServerResponseException

enum class ErrorType(val value: String) {
    Server("server"),
    Client("client"),
    Generic("generic");
}

fun Throwable.toErrorType() = when (this) {
    is ServerResponseException -> when (this.response.status.value) {
        in 401..499 -> ErrorType.Client
        in 500..599 -> ErrorType.Server
        else -> ErrorType.Generic
    }
    else -> ErrorType.Generic
}