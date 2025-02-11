package com.example.appmovies.core.network

import java.lang.Exception

sealed interface OperationStatus<T>{
    data class Success<T>(val value : T) : OperationStatus<T>
    data class Failure<T> (val exception: Exception) :OperationStatus<T>
}

