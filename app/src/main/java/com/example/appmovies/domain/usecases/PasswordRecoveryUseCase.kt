package com.example.appmovies.domain.usecases

import com.example.appmovies.core.network.OperationStatus
import com.example.appmovies.domain.repository.FirebaseRepository

class PasswordRecoveryUseCase(
    private val firebaseRepository: FirebaseRepository
) {
    suspend fun execute(
        email: String
    ): OperationStatus<Unit> {
        return firebaseRepository.passwordRecovery(email)
    }
}