package com.example.appmovies.domain.usecases

import com.example.appmovies.core.network.OperationStatus
import com.example.appmovies.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseUser

class SignupUserUseCase(
    private val firebaseRepository: FirebaseRepository
) {
    suspend fun execute(
        username: String,
        email: String,
        password: String,
    ): OperationStatus<FirebaseUser> {
        return firebaseRepository.signUpUser(username, email, password)
    }
}