package com.example.appmovies.di

import com.example.appmovies.presentation.auth.login.LoginViewModel
import com.example.appmovies.presentation.auth.passwordRecovery.PasswordRecoveryViewModel
import com.example.appmovies.presentation.auth.signup.SignupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::SignupViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::PasswordRecoveryViewModel)
}