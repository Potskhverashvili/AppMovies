package com.example.appmovies.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.network.OperationStatus
import com.example.appmovies.domain.usecases.SignupUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupUserUseCase: SignupUserUseCase
) : ViewModel() {

    private val _signupFlow = MutableSharedFlow<String>()
    val signupFlow = _signupFlow.asSharedFlow()

    private val _showError = MutableSharedFlow<String?>()
    val showError = _showError.asSharedFlow()

    private val _isLoadingState = MutableSharedFlow<Boolean>()
    val isLoadingState = _isLoadingState.asSharedFlow()


    fun signupUser(username: String, email: String, password: String) = viewModelScope.launch {
        _isLoadingState.emit(true)
        when (val status = signupUserUseCase.execute(username, email, password)) {
            is OperationStatus.Success -> {
                _signupFlow.emit(status.value.email.toString())
            }

            is OperationStatus.Failure -> {
                _showError.emit(status.exception.message)
            }
        }
        _isLoadingState.emit(false)
    }

    fun isSignUpInputValid(
    username: String,
    email: String,
    password: String,
    repeatPassword: String
    ): Boolean {
        if (username.isBlank() || email.isBlank() || password.isBlank() || repeatPassword.isBlank()) {
            viewModelScope.launch { _showError.emit("Please fill in all fields") }
            return false
        }
        if (password != repeatPassword) {
            viewModelScope.launch { _showError.emit("Passwords do not match") }
            return false
        }
        return true
    }

}