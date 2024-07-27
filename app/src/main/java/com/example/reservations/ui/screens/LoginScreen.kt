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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.reservations.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.login_toolbar_title)) })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = {
                    // TODO: Make login API call for provider
                    navController.navigate("providerSchedule")
                }
            ) {
                Text(text = stringResource(R.string.login_provider_login))
            }

            Button(
                onClick = {
                    // TODO: Make login API call for client
                    navController.navigate("clientSelection")
                }
            ) {
                Text(text = stringResource(R.string.login_client_login))
            }

        }
    }
}


@Preview
@Composable
private fun GenericLoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}
