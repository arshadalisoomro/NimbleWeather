package com.being.coder.app.nw

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.being.coder.app.nw.data.dto.CurrentConditionDto
import com.being.coder.app.nw.ui.home.HomeViewModel
import com.being.coder.app.nw.ui.theme.NimbleWeatherTheme
import com.being.coder.app.nw.util.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NimbleWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        CurrentConditionScreen(viewModel, Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentConditionScreen(viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    val currentConditionState by viewModel.getCurrentConditionWithState.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        Button(onClick = { viewModel.getCurrentCondition("sukkur") }) {
            Text(text = "Search for Sukkur")
        }

        // Display the current condition based on the state
        when (currentConditionState) {
            is Response.Loading -> Text("Loading...")
            is Response.Success -> {
                val currentCondition = (currentConditionState as Response.Success<CurrentConditionDto>).data
                Log.e("CURRENT_CONDITION", currentCondition.name)
                Text("Current Condition: ${currentCondition.name}")
            }
            is Response.Error -> {
                val error = (currentConditionState as Response.Error).message
                Text("Error: $error")
            }
        }
    }
}