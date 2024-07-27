package com.example.reservations.fakes

import com.example.reservations.viewmodels.ClientSelectionViewModel

class FakeClientSelectionViewModel : ClientSelectionViewModel(
    providerRepository = FakeProviderRepository()
) {
    init {
        selectedTimeSlot = "10:00 AM"
        selectedDate = "2024-07-25"
        providerName = "Dr. Smith"
    }
}
