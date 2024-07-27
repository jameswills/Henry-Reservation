package com.example.reservations.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject

@HiltViewModel
class ProviderScheduleViewModel @Inject constructor() : ViewModel() {

    private val _selectedDateInMillis = MutableStateFlow<Long?>(null)
    val selectedDateInMillis: StateFlow<Long?> = _selectedDateInMillis.asStateFlow()

    fun onDateSelected(selectedDateInMillis: Long?) {
        _selectedDateInMillis.value = selectedDateInMillis
    }
}
