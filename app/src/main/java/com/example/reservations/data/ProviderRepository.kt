package com.example.reservations.data

interface ProviderRepository {
    fun getProviderAvailability(): ProviderAvailability?
}