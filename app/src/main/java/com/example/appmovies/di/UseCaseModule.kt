package com.example.appmovies.di

import com.example.appmovies.domain.usecases.LoginUserUsaCase
import com.example.appmovies.domain.usecases.PasswordRecoveryUseCase
import com.example.appmovies.domain.usecases.SignupUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SignupUserUseCase(get()) }
    factory { LoginUserUsaCase(get()) }
    factory { PasswordRecoveryUseCase(get()) }
}