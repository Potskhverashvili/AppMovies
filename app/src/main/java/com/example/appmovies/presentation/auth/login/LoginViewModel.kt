package com.example.appmovies.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.network.OperationStatus
import com.example.appmovies.domain.usecases.LoginUserUsaCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUsaCase: LoginUserUsaCase
) : ViewModel() {

    private val _loginFlow = MutableSharedFlow<String>()
    var loginFlow: SharedFlow<String> = _loginFlow

    private val _showError = MutableSharedFlow<String?>()
    val showError: SharedFlow<String?> = _showError

    private val _isLoadingState = MutableSharedFlow<Boolean>()
    val isLoadingState: SharedFlow<Boolean> = _isLoadingState

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _isLoadingState.emit(true)
        when (val status = loginUserUsaCase.execute(email, password)) {
            is OperationStatus.Success -> {
                _loginFlow.emit(status.value.toString())
            }

            is OperationStatus.Failure -> {
                _showError.emit(status.exception.message)
            }
        }
        _isLoadingState.emit(false)
    }

    fun isLoginInputValid(email: String, password: String): Boolean {
        if (email.isBlank() || password.isBlank()) {
            viewModelScope.launch { _showError.emit("Please fill in all fields") }
            return false
        }
        return true
    }
}