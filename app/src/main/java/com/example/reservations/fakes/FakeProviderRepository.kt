package com.example.reservations.fakes

import com.example.reservations.data.AvailableDay
import com.example.reservations.data.ProviderAvailability
import com.example.reservations.data.ProviderRepository
import com.example.reservations.data.TimeSlot

class FakeProviderRepository : ProviderRepository {
    override fun getProviderAvailability(): ProviderAvailability? {
        return ProviderAvailability(
            providerName = "Dr. Smith",
            providerId = 1234,
            availableSlots = listOf(
                AvailableDay(
                    date = "2024-07-25",
                    timeSlots = listOf(
                        TimeSlot("10:00"),
                        TimeSlot("11:00")
                    )
                )
            )
        )
    }
}