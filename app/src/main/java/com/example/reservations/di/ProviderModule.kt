package com.example.reservations.di

import com.example.reservations.data.ProviderRepository
import com.example.reservations.data.RealProviderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProviderModule {

    @Binds
    @Singleton
    abstract fun bindProviderRepository(
        realProviderRepository: RealProviderRepository
    ): ProviderRepository
}