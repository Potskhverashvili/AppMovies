package com.example.appmovies.di

import com.example.appmovies.data.repository.FirebaseRepositoryImpl
import com.example.appmovies.domain.repository.FirebaseRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::FirebaseRepositoryImpl) bind FirebaseRepository::class
}