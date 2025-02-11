package com.example.appmovies.presentation.auth.passwordRecovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.network.OperationStatus
import com.example.appmovies.domain.usecases.PasswordRecoveryUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class PasswordRecoveryViewModel(
    private val passwordRecoveryUseCase: PasswordRecoveryUseCase
) : ViewModel() {

    private val _isEmailSend = MutableSharedFlow<Boolean>()
    val isEmailSend: SharedFlow<Boolean> = _isEmailSend

    private val _showError = MutableSharedFlow<String?>()
    val showError: SharedFlow<String?> = _showError

    private val _isLoadingState = MutableSharedFlow<Boolean>()
    val isLoadingState: SharedFlow<Boolean> = _isLoadingState

    fun passwordRecovery(email: String) = viewModelScope.launch {
        _isLoadingState.emit(true)
        when (val status = passwordRecoveryUseCase.execute(email)) {
            is OperationStatus.Success -> {
                _isEmailSend.emit(true)
            }

            is OperationStatus.Failure -> {
                _showError.emit(status.exception.toString())
            }
        }
        _isLoadingState.emit(false)
    }

    fun isEmailValid(email: String): Boolean {
        return email.isNotEmpty()
    }
}