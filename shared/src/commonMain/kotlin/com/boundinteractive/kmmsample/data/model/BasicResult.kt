package com.boundinteractive.kmmsample.data.model

sealed class BasicResult<out T> {
    data class Success<out T>(val value: T) : BasicResult<T>()
    data class Error(val error: ErrorType) : BasicResult<Nothing>()
}