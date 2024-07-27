package com.example.reservations.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProviderAvailability(
    val providerId: Int,
    val providerName: String,
    val availableSlots: List<AvailableDay>
)

@JsonClass(generateAdapter = true)
data class AvailableDay(
    val date: String,
    val timeSlots: List<TimeSlot>
)

@JsonClass(generateAdapter = true)
data class TimeSlot(
    val availableTime: String
)