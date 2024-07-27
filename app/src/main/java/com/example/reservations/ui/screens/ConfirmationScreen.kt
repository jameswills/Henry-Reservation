package com.example.reservations.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reservations.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationScreen(
    navController: NavController,
    selectedDate: String,
    selectedTimeSlot: String,
    providerName: String
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.confirmation_toolbar_title)) })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ConfirmWithProviderText(providerName)
            ConfirmDateText(selectedDate)
            ConfirmTimeText(selectedTimeSlot)
            ConfirmAndReserveButton(navController)
        }
    }
}

@Composable
private fun ConfirmWithProviderText(providerName: String) {
    Text(
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
        text = stringResource(R.string.confirmation_confirm_text, providerName),
    )
}

@Composable
private fun ConfirmDateText(selectedDate: String) {
    Text(
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
        text = stringResource(R.string.confirm_date_text, selectedDate)
    )
}

@Composable
private fun ConfirmTimeText(selectedTimeSlot: String) {
    Text(
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
        text = stringResource(R.string.confirmation_time_text, selectedTimeSlot)
    )
}

@Composable
private fun ConfirmAndReserveButton(navController: NavController) {
    Button(
        onClick = {
            // TODO: Make reservation confirmation API call
            // Update the ViewModel accordingly to reflect reservation status
            navController.navigate("login") {
                popUpTo("login") { inclusive = true } // Clear the back stack up to login
            }
        }
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.confirm_reserve_button_text)
        )
    }
}

@Preview
@Composable
private fun ConfirmationScreenPreview() {
    ConfirmationScreen(
        navController = NavController(LocalContext.current),
        selectedDate = "2023-08-01",
        selectedTimeSlot = "10:30",
        providerName = "Test Provider"
    )
}
