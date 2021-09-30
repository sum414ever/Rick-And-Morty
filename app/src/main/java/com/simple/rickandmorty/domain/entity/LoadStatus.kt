package com.simple.rickandmorty.domain.entity

open class LoadStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : LoadStatus<T>(data)
    class Loading<T>(data: T? = null) : LoadStatus<T>(data)
    class Error<T>(message: String, data: T? = null) : LoadStatus<T>(data, message)
}