package com.example.appmovies.domain.usecases

import com.example.appmovies.core.network.OperationStatus
import com.example.appmovies.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseUser

class LoginUserUsaCase(
    private val firebaseRepository: FirebaseRepository
) {
    suspend fun execute(
        email: String, password: String
    ): OperationStatus<FirebaseUser> {
        return firebaseRepository.loginInUser(email, password)
    }
}