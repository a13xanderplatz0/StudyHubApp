package com.example.studyhubapp.domain.usecases

interface BaseUseCase<in P, out R> {
    suspend operator fun invoke(params: P): R
}
