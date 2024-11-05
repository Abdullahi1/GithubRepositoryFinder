package com.example.data.di

import com.example.data.repository.GithubRepoRepositoryImpl
import com.example.data.user.GithubUserRepositoryImpl
import com.example.domain.repository.GithubRepoRepository
import com.example.domain.user.GithubUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindGithubRepoRepository(
        impl: GithubRepoRepositoryImpl,
    ): GithubRepoRepository

    @Binds
    fun bindGithubUserRepository(
        impl: GithubUserRepositoryImpl,
    ): GithubUserRepository
}