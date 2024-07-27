package com.example.reservations.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reservations.data.AvailableDay
import com.example.reservations.data.ProviderAvailability
import com.example.reservations.data.ProviderRepository
import com.example.reservations.data.TimeSlot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
open class ClientSelectionViewModel @Inject constructor(
    private val providerRepository: ProviderRepository
) : ViewModel() {

    var providerAvailability = mutableStateOf<ProviderAvailability?>(null)
    var selectedTimeSlot by mutableStateOf("")
    var selectedDate by mutableStateOf("")
    var providerName by mutableStateOf("")

    init {
        loadProviderAvailability()
    }

    /**
     * Typically we would have an API setup to fetch the provider availability from a server.
     * This would also typically be done further upstream in a ViewModel outside of the Composable
     * and the data needed would be passed in as a parameter.
     * But for the purposes of this demo, we'll just hardcode it in an asset file and retrieve it here.
     */
    private fun loadProviderAvailability() {
        viewModelScope.launch {
            providerAvailability.value = providerRepository.getProviderAvailability()
        }
    }

    // Filter out time slots that are within the next 24 hours.
    fun getFilteredTimeSlots(availableSlots: AvailableDay): List<TimeSlot> {
        val currentDateTime = LocalDateTime.now()
        return availableSlots.timeSlots.filter { timeSlot ->
            val dateTimeString = "${availableSlots.date} ${timeSlot.availableTime}"
            val dateTime = LocalDateTime.parse(
                dateTimeString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm")
            )
            dateTime.isAfter(currentDateTime.plusHours(24))
        }
    }

    fun updateSelection(timeSlot: String, date: String, providerName: String) {
        this.selectedTimeSlot = timeSlot
        this.selectedDate = date
        this.providerName = providerName
    }
}