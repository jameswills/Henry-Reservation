package com.example.reservations.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reservations.R
import com.example.reservations.viewmodels.ClientSelectionViewModel
import com.example.reservations.fakes.FakeClientSelectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSelectionScreen(navController: NavController, viewModel: ClientSelectionViewModel) {
    var selectedTimeIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.client_selection_toolbar_title)) })
        },
        bottomBar = {
            BottomAppBar {
                ConfirmButton(
                    navController = navController,
                    selectedTimeIndex = selectedTimeIndex
                )
            }
        }
    ) { innerPadding ->


        viewModel.providerAvailability.value?.let { providerAvailability ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                providerAvailability.availableSlots.forEach { availableSlots ->

                    TitleText(
                        providerName = providerAvailability.providerName,
                        date = availableSlots.date
                    )

                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        val filteredTimeSlots = viewModel.getFilteredTimeSlots(availableSlots)

                        if (filteredTimeSlots.isNotEmpty()) {
                            itemsIndexed(items = filteredTimeSlots) { index, timeSlot ->
                                SelectableTimeSlotButton(
                                    timeSlot = timeSlot.availableTime,
                                    isSelected = selectedTimeIndex == index,
                                    index = index,
                                    onSelect = { newIndex ->
                                        selectedTimeIndex =
                                            if (selectedTimeIndex == newIndex) null else newIndex
                                        viewModel.updateSelection(
                                            timeSlot.availableTime,
                                            availableSlots.date,
                                            providerAvailability.providerName
                                        )
                                    }
                                )
                            }
                        } else {
                            item(span = { GridItemSpan(2) }) {
                                NoAppointmentsAvailableText()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TitleText(providerName: String, date: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = stringResource(R.string.client_selection_provider_title, providerName, date),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun SelectableTimeSlotButton(
    timeSlot: String,
    isSelected: Boolean,
    index: Int,
    onSelect: (Int) -> Unit
) {
    OutlinedButton(
        onClick = { onSelect(index) },
        modifier = Modifier.padding(4.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isSelected) Color.White else Color.Black,
            containerColor = if (isSelected) Color.Blue else Color.Transparent
        )
    ) {
        Text(text = timeSlot)
    }
}

@Composable
private fun NoAppointmentsAvailableText() {
    Text(
        text = stringResource(R.string.client_selection_no_appts_available),
        modifier = Modifier.padding(16.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 24.sp)
    )
}

@Composable
private fun ConfirmButton(
    navController: NavController,
    selectedTimeIndex: Int? = null,
) {
    Button(
        onClick = { navController.navigate("confirmation") },
        enabled = selectedTimeIndex != null,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.client_selection_continue_button)
        )
    }
}

@Preview
@Composable
private fun ClientSelectionScreenScreenPreview() {
    ClientSelectionScreen(
        navController = NavController(LocalContext.current),
        viewModel = FakeClientSelectionViewModel()
    )
}
