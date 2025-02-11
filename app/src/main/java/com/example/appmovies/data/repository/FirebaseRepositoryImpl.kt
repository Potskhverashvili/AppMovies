package com.example.appmovies.data.repository

import android.net.Uri
import com.example.appmovies.core.network.FirebaseCallHelper
import com.example.appmovies.core.network.OperationStatus
import com.example.appmovies.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : FirebaseRepository {

    override suspend fun signUpUser(
        username: String,
        email: String,
        password: String
    ): OperationStatus<FirebaseUser> {
        return FirebaseCallHelper.safeFirebaseCall {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user

            val userMap = hashMapOf(
                "username" to username,
                "email" to email
            )
            firestore.collection("users").document(user!!.uid).set(userMap).await()
            user
        }
    }

    override suspend fun loginInUser(
        email: String,
        password: String
    ): OperationStatus<FirebaseUser> {
        return FirebaseCallHelper.safeFirebaseCall {
            val resultUser = auth.signInWithEmailAndPassword(email, password).await()
            resultUser.user!!
        }
    }

    override suspend fun passwordRecovery(email: String): OperationStatus<Unit> {
        return FirebaseCallHelper.safeFirebaseCall {
            auth.sendPasswordResetEmail(email).await()
        }
    }

    override suspend fun getUsername(): OperationStatus<String> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUsername(updateName: String): OperationStatus<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserEmail(): OperationStatus<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun logOut(): OperationStatus<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadImageToFireStore(uri: Uri): OperationStatus<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserProfileImage(): OperationStatus<String> {
        TODO("Not yet implemented")
    }
}