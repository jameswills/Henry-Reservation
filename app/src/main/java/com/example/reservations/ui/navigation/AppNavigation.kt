package com.example.reservations.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reservations.ui.screens.ClientSelectionScreen
import com.example.reservations.ui.screens.ConfirmationScreen
import com.example.reservations.ui.screens.LoginScreen
import com.example.reservations.ui.screens.ProviderScheduleScreen
import com.example.reservations.viewmodels.ClientSelectionViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val clientSelectionViewModel: ClientSelectionViewModel  = hiltViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("clientSelection") {
            ClientSelectionScreen(
                navController = navController,
                viewModel = clientSelectionViewModel
            )
        }
        composable("confirmation") {
            ConfirmationScreen(
                navController = navController,
                selectedDate = clientSelectionViewModel.selectedDate,
                selectedTimeSlot = clientSelectionViewModel.selectedTimeSlot,
                providerName = clientSelectionViewModel.providerName
            )
        }
        composable("login") { LoginScreen(navController = navController) }
        composable("providerSchedule") { ProviderScheduleScreen(navController = navController) }
    }
}
